package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;

import org.example.APIREST.Equipo;
import org.example.APIREST.Prestamo;
import org.example.APIREST.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Route
@PWA(name = "App", shortName = "App")
public class WEB extends VerticalLayout {

    //private static final String API = "http://localhost:9090/%s";
    private static final String API = "https://p3backdis.herokuapp.com//%s";
    //private static final String API = "http://api:9090/%s";
    HttpRequest request;
    HttpClient cliente = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    private void IniciamosUsers(){
        String resource = String.format(API, "iniciarid");
        System.out.println(resource);


        try{
            request = HttpRequest.newBuilder(new URI(resource)).GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return;

    }

    private String Getusers(){
        String resource = String.format(API, "Getusers");
        System.out.println(resource);

        try{
            request = HttpRequest.newBuilder(new URI(resource)).header("Content-type","application/java")
                    .GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return response.body();

    }

    private String crearUser(User newUser){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String string = gson.toJson(newUser, User.class);
        String resource = String.format(API, "nuevoUsuario");
        System.out.println(resource);
        System.out.println("Nuestro gson: \n" + string);

        try{
            request = HttpRequest.newBuilder(new URI(resource)).header("Content-type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(string)).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return response.body();

    }

    private String editaruser(User userEditado){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = gson.toJson(userEditado, User.class);

        String resource = String.format(API, "editarUser");
        System.out.println("User editado en el gson: \n"+string);

        try{
            request = HttpRequest.newBuilder(new URI(resource)).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(string)).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return response.body();
    }

    private String Getprest(){
        String resource = String.format(API, "Getprestamos");
        System.out.println(resource);

        try{
            request = HttpRequest.newBuilder(new URI(resource)).header("Content-type","application/java")
                    .GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return response.body();

    }

    private String crearPrestamo(Prestamo newPrest){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String string = gson.toJson(newPrest, Prestamo.class);
        String resource = String.format(API, "nuevoPrestamo");
        System.out.println(resource);
        System.out.println("Nuestro gson: \n" + string);

        try{
            request = HttpRequest.newBuilder(new URI(resource)).header("Content-type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(string)).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return response.body();

    }

    private String Getequipo(){
        String resource = String.format(API, "Getequipos");
        System.out.println(resource);

        try{
            request = HttpRequest.newBuilder(new URI(resource)).header("Content-type","application/java")
                    .GET().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try{
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());

        return response.body();

    }
/////////////////////////////////////////////////////////
    public WEB(){

        IniciamosUsers();
        /////////////////////////////USUARIOS//////////////////////////////////
        //Recibir los usuarios
        String usersST = Getusers();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<User> users;
        Type ListaUsersType = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(usersST, ListaUsersType);

        //Tabla de los usuarios
        Tab usersTAB = new Tab("Users");
        Div usersDIV = new Div();

        //Definicion del grid de users
        Grid<User> gridUsers = new Grid<>(User.class);
        gridUsers.setItems(users);
        gridUsers.removeColumnByKey("id");
        gridUsers.setColumns("nombre", "departamento", "telefono", "email", "ubicacion");

        //Definicion de los layaout
        VerticalLayout usersVLayout = new VerticalLayout();
        HorizontalLayout usersHLayout = new HorizontalLayout();

        //Definicion de los textfield
        AtomicInteger id_user = new AtomicInteger();
        TextField nombre = new TextField("Nombre");
        TextField departamento = new TextField("Departamento");
        TextField telefono = new TextField("Telefono");
        TextField email = new TextField("Email");
        TextField ubicacion = new TextField("Ubicación");

        //Definicion de los buttons
        Button anadir = new Button("Añadir");
        Button acep_2 = new Button("Aceptar_2");
        Button nuevo = new Button("Nuevo User");
        Button edit = new Button("Editar User");
        Button borrar = new Button("Borrar User");

        //Definicion del diseño de los layout y divs
        usersHLayout.add(nombre, departamento, telefono, email, ubicacion);
        usersVLayout.add(usersHLayout, nuevo);
        usersDIV.add(gridUsers, nuevo);

        //Control de interacciones
        gridUsers.addItemClickListener(e->{
            id_user.set(e.getItem().getId());
            nombre.clear();
            departamento.clear();
            telefono.clear();
            email.clear();
            ubicacion.clear();
            usersDIV.add(usersVLayout);
            nombre.getValue();
            departamento.getValue();
            telefono.getValue();
            email.getValue();
            ubicacion.getValue();
            usersVLayout.remove(anadir);
            usersVLayout.remove(acep_2);
            usersVLayout.add(edit);
            usersVLayout.remove(borrar);
        });

        nuevo.addClickListener(e->{
            usersDIV.add(usersVLayout);
            nombre.clear();
            departamento.clear();
            telefono.clear();
            email.clear();
            ubicacion.clear();
            usersVLayout.remove(edit);
            usersVLayout.remove(acep_2);
            usersVLayout.remove(borrar);
            usersVLayout.add(anadir);

        });

        edit.addClickListener(e->{
           usersDIV.remove(usersVLayout);

           User userEditado = new User(id_user.get(), nombre.getValue(), departamento.getValue(), telefono.getValue(), email.getValue(), ubicacion.getValue());

           editaruser(userEditado);

           UI.getCurrent().getPage().reload();
        });

        borrar.addClickListener(e->{
           usersDIV.remove(usersVLayout);
        });

        anadir.addClickListener(e->{
            usersDIV.remove(usersVLayout);

            User nuevoUsuario = new User(999, nombre.getValue(), departamento.getValue(), telefono.getValue(), email.getValue(), ubicacion.getValue());
            crearUser(nuevoUsuario);
            UI.getCurrent().getPage().reload();
        });

        acep_2.addClickListener(e->{
           usersDIV.remove(usersVLayout);
        });
        /////////////////////////////PRESTAMOS//////////////////////////////////
        //Recibir los usuarios
        String presST = Getprest();
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Prestamo> prestamos;
        Type prestType = new TypeToken<ArrayList<Prestamo>>(){}.getType();
        prestamos = gson.fromJson(presST, prestType);

        //Tabla de los usuarios
        Tab prestTAB = new Tab("Prestamos");
        Div prestDIV = new Div();
        prestDIV.setVisible(false);

        //Definicion del grid de users
        Grid<Prestamo> gridPrest = new Grid<>(Prestamo.class);
        gridPrest.setItems(prestamos);
        gridPrest.removeColumnByKey("id");
        gridPrest.setColumns("id_user","id_equipo" ,"fecha_inicio", "fecha_fin", "fecha_real", "comentario");

        //Definicion de los layaout
        VerticalLayout prestVLayout = new VerticalLayout();
        HorizontalLayout prestHLayout = new HorizontalLayout();

        //Definicion de los textfield
        TextField iduser = new TextField("id_user");
        TextField idequipo = new TextField("id_equipo");
        TextField fechaIni = new TextField("fecha_inicio");
        TextField fechaFin = new TextField("fecha_fin");
        TextField fechaReal = new TextField("fecha_real");
        TextField comen = new TextField("comentario");

        //Definicion de los buttons
        Button anadir_1 = new Button("Añadir");
        Button acep_2_2 = new Button("Aceptar_2");
        Button nuevo_2 = new Button("Nuevo Prestamo");
        Button edit_2 = new Button("Editar Prestamo");


        //Definicion del diseño de los layout y divs
        prestVLayout.add(prestHLayout, nuevo_2);
        prestHLayout.add(iduser, idequipo, fechaIni, fechaFin, fechaReal, comen);
        prestDIV.add(gridPrest, nuevo_2);

        //Control de interacciones
        gridPrest.addItemClickListener(e->{
            iduser.clear();
            idequipo.clear();
            fechaIni.clear();
            fechaFin.clear();
            fechaReal.clear();
            comen.clear();
            prestDIV.add(prestVLayout);
            iduser.getValue();
            idequipo.getValue();
            fechaIni.getValue();
            fechaFin.getValue();
            fechaReal.getValue();
            comen.getValue();
            prestVLayout.remove(anadir_1);
            prestVLayout.remove(acep_2_2);
            prestVLayout.add(edit_2);
        });

        nuevo_2.addClickListener(e->{
            prestDIV.add(prestVLayout);
            iduser.clear();
            idequipo.clear();
            fechaIni.clear();
            fechaFin.clear();
            fechaReal.clear();
            comen.clear();
            prestDIV.add(prestVLayout);
            prestVLayout.remove(edit_2);
            prestVLayout.remove(acep_2_2);
            prestVLayout.add(anadir_1);

        });

        edit_2.addClickListener(e->{
            prestDIV.remove(prestVLayout);
        });

        anadir_1.addClickListener(e->{
            prestDIV.remove(prestVLayout);

            Prestamo nuevoPrestamo = new Prestamo(999, Integer.parseInt(iduser.getValue()),Integer.parseInt(idequipo.getValue()), fechaIni.getValue(), fechaFin.getValue(), fechaReal.getValue(), comen.getValue());
            crearPrestamo(nuevoPrestamo);
            UI.getCurrent().getPage().reload();
        });

        acep_2_2.addClickListener(e->{
            prestDIV.remove(prestVLayout);
        });
        /////////////////////////////EQUIPOS//////////////////////////////////
        //Recibir los usuarios
        String equiST = Getequipo();
        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Equipo> equipos;
        Type equiType = new TypeToken<ArrayList<Equipo>>(){}.getType();
        equipos = gson.fromJson(equiST, equiType);

        //Tabla de los usuarios
        Tab equiTAB = new Tab("Equipos");
        Div equiDIV = new Div();
        equiDIV.setVisible(false); /////IMPORTANTE

        //Definicion del grid de users
        Grid<Equipo> gridEqui = new Grid<>(Equipo.class);
        gridEqui.setItems(equipos);
        gridEqui.removeColumnByKey("id_equipo");
        gridEqui.setColumns("tipo", "marca", "uso", "so_nombre", "so_version", "procesador", "ram", "ram_speed", "tipo_disco",
                "capacidad", "diagonal", "resolucion", "software_pago", "software_pago_v", "software_pago_t", "software_libre",
                "software_libre_v");

        //Definicion de los layaout
        VerticalLayout equiVLayout = new VerticalLayout();
        HorizontalLayout equiHLayout = new HorizontalLayout();

        //Definicion de los textfield
        TextField tipo = new TextField("Tipo");
        TextField marca = new TextField("Marca");
        TextField uso = new TextField("Uso");
        TextField so_nombre = new TextField("SO_Nombre");
        TextField so_version = new TextField("SO_Version");
        TextField procesador = new TextField("procesador");
        TextField ram = new TextField("ram");
        TextField ram_speed = new TextField("ram_speed");
        TextField tipo_disco = new TextField("tipo_disco");
        TextField capacidad = new TextField("capacidad");
        TextField diagonal = new TextField("diagonal");
        TextField resolucion = new TextField("resolucion");
        TextField software_pago = new TextField("software_pago");
        TextField software_pago_v = new TextField("software_pago_v");
        TextField software_pago_t = new TextField("software_pago_t");
        TextField software_libre = new TextField("software_libre");
        TextField software_libre_v = new TextField("software_libre_v");

        gridEqui.addItemClickListener(e->{
            tipo.setValue(e.getItem().getTipo());
            marca.setValue(e.getItem().getMarca());
            uso.setValue(e.getItem().getUso());
            so_nombre.setValue(e.getItem().getSo_nombre());
            so_version.setValue(e.getItem().getSo_version());
            procesador.setValue(e.getItem().getProcesador());
            ram.setValue(String.valueOf(e.getItem().getRam()));
            ram_speed.setValue(String.valueOf(e.getItem().getRam_speed()));
            tipo_disco.setValue(e.getItem().getTipo_disco());
            diagonal.setValue(String.valueOf(e.getItem().getDiagonal()));
            resolucion.setValue(e.getItem().getResolucion());
            software_pago.setValue(e.getItem().getSoftware_pago());
            software_pago_v.setValue(e.getItem().getSoftware_pago_v());
            software_pago_t.setValue(e.getItem().getSoftware_pago_t());
            software_libre.setValue(e.getItem().getSoftware_libre());
            software_libre_v.setValue(e.getItem().getSoftware_libre_v());
        });
        //Definicion del diseño de los layout y divs
        equiVLayout.add(equiHLayout);
        equiHLayout.add(tipo, marca, uso, so_nombre, so_version, procesador, ram, ram_speed, tipo_disco, capacidad, diagonal,
                resolucion, software_pago, software_pago_v, software_pago_t, software_libre, software_libre_v);
        equiDIV.add(gridEqui);


   ////////////////////////////////////////////////////////////////
        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(usersTAB, usersDIV);
        tabsToPages.put(prestTAB, prestDIV);
        tabsToPages.put(equiTAB, equiDIV);
        Tabs tabs = new Tabs(usersTAB, prestTAB, equiTAB);
        tabs.setSizeFull();
        Div pages = new Div(usersDIV, prestDIV, equiDIV);
        pages.setSizeFull();


        tabs.addSelectedChangeListener(event ->{
           tabsToPages.values().forEach(page -> page.setVisible(false));
           Component paginaSeleccionada = tabsToPages.get(tabs.getSelectedTab());
           paginaSeleccionada.setVisible(true);
        });

        add(tabs, pages);

    }
}
