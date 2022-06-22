package com.example.file.service;

import com.example.file.exception.FileException;
import com.example.file.utils.FileUtil;
import com.example.file.domain.Order;
import com.example.file.domain.Product;
import com.example.file.domain.User;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ProcessorService {

    @Value("${diretorioArquivo.diretorioEntrada}")
    private String diretorioEntrada;

    @Value("${diretorioArquivo.diretorioSaida}")
    private String diretorioSaida;

    public void startProcessor() {
        LocalDateTime inicioProcessamento = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        log.info("------------------------------------------------------");
        log.info("Iniciando o processamento dos arquivos ... " + inicioProcessamento.format(formatter));
        log.info("Procurando arquivos para processar no diretorio: " + diretorioEntrada + " ...");

        List<File> listaArquivos;
        try {
            FileUtil fileUtil = new FileUtil();
            listaArquivos = fileUtil.getFileByPath(System.getProperty("user.dir") + diretorioEntrada);
        } catch (Exception e) {
            throw new FileException("Erro ao pegar os arquivos do diretório");
        }

        if (!listaArquivos.isEmpty()) {
            try {
                processFiles(listaArquivos);
            } catch (IOException e) {
                throw new FileException("Erro ao processar os arquivos");
            }
        }
        LocalDateTime fimProcessamento = LocalDateTime.now();
        log.info("Finalizando o processamento dos arquivos... " + fimProcessamento.format(formatter) );
        log.info("------------------------------------------------------");
    }

    private void processFiles(List<File> files) throws IOException {
        List<User> users = new ArrayList<>();
        for (File file : files) {
            BufferedReader dados = new BufferedReader(new FileReader(file));
            String line = dados.readLine();
            while (line != null) {
                Integer id_usuario = Integer.valueOf(line.substring(0, 10).trim());
                String nome = line.substring(10, 55).trim();
                Integer id_pedido = Integer.valueOf(line.substring(55, 65).trim());
                Integer id_produto = Integer.valueOf(line.substring(65, 75).trim());
                BigDecimal valor_produto = new BigDecimal(line.substring(75, 87).trim());
                String data_compra = String.valueOf(LocalDate.parse(line.substring(87, 95).trim(), DateTimeFormatter.ofPattern("yyyyMMdd")));

                Product product = new Product();
                product.setProduct_id(id_produto);
                product.setValues(valor_produto);
                List<Product> products = new ArrayList<>();
                products.add(product);

                Order order = new Order();
                order.setOrder_id(id_pedido);
                order.setOrder_date(data_compra);
                order.setOrder_total(valor_produto);
                order.setProducts(products);
                List<Order> orders = new ArrayList<>();
                orders.add(order);

                User user = new User();
                user.setUser_id(id_usuario);
                user.setName(nome);
                user.setOrders(orders);

                if (users.contains(user)) {
                    if (users.get(users.indexOf(user)).getOrders().contains(order)) {
                        List<Order> pedidosExistente = users.get(users.indexOf(user)).getOrders();
                        List<Product> produtoExistente = pedidosExistente.get(pedidosExistente.indexOf(order)).getProducts();

                        BigDecimal totalPedido = pedidosExistente.get(pedidosExistente.indexOf(order)).getOrder_total();
                        pedidosExistente.get(pedidosExistente.indexOf(order)).setOrder_total(totalPedido.add(product.getValues()));
                        if (produtoExistente.contains(product)) {
                            Integer quantidade = produtoExistente.get(produtoExistente.indexOf(product)).getQtd();
                            produtoExistente.get(produtoExistente.indexOf(product)).setQtd(quantidade + 1);
                        } else {
                            pedidosExistente.get(pedidosExistente.indexOf(order)).getProducts().add(product);
                        }
                    } else {
                        users.get(users.indexOf(user)).getOrders().add(order);
                    }
                } else {
                    users.add(user);
                }

                line = dados.readLine();
            }

            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            returnObjectToJson(users, fileName);
        }
    }

    private void returnObjectToJson(List<User> users, String fileName) {
        String json = new Gson().toJson(users);
        FileUtil fileUtil = new FileUtil();
        fileUtil.generateFileInPath(json, fileName, System.getProperty("user.dir") + diretorioSaida);
    }
}