package JOANGALMES.controler;

import principal.demo.DAO.AproachDAOImpl;
import JOANGALMES.service.TipusService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TipusControleler extends HttpServlet {

    TipusService tipusService;

    // Al ser una capa superior no se le puede hacer una injeccion de dependencias
    public TipusControleler() {
        this.tipusService = new TipusService(new AproachDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
