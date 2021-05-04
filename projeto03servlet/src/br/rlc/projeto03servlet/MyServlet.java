package br.rlc.projeto03servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		MoradorCRUD crud = new MoradorCRUD();
		crud.setup();
		
		List<Morador> listaMoradores = crud.listAll();
		
		PrintWriter printWriter = response.getWriter();
		printWriter.println(
			"<!DOCTYPE html>" +
			"<html>" +
				"<head>" +
					"<title>Listar tudo</title>" +
					"<style>table, th, td {border: 1px solid black;border-collapse: collapse;}th, td {padding: 5px;text-align: left;}</style>" +
				"</head>" +
				"<body>" +
					"<h1>Listar tudo</h1>" +
					"<hr/>" + 
						"<table style=\"width:100%\">" +
						"<tr>" +
						    "<th>ID</th>" +
						    "<th>Apartamento</th>" +
						    "<th>PIN</th>" +
						"</tr>"
		);
		
		for (Morador m : listaMoradores) {
			printWriter.println("<tr><td>" + m.getId() + "</td>" + "<td>" + m.getApartamento() + "</td>" + "<td>" + m.getPin() + "</td></tr>");
		}

		printWriter.println("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		MoradorCRUD crud = new MoradorCRUD();
		crud.setup();
		
		int count = 0;
		String apartamento = request.getParameter("apartamento");
		List<Morador> listaMoradores = crud.listAll();
		
		for (Morador m : listaMoradores) {
			if (apartamento.equals(m.getApartamento())) {
				sucesso(response, m);
				break;
			}
			count++;
		}
		
		if (count == listaMoradores.size()) {
			response.sendRedirect("erro.html");
		}
	}
	
	public void sucesso(HttpServletResponse response, Morador m) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.append(
			"<!DOCTYPE html>" +
			"<html>" +
				"<head>" +
					"<title>Consulta efetuada com sucesso</title>" +
				"</head>" +
				"<body>" +
					"<h1>Consulta efetuada com sucesso</h1>" +
					"<hr/>" +
					"<p>O PIN do apartamento <b>" + m.getApartamento() + "</b> é: " + m.getPin() + "</p>" +
					"<a href=\"index.html\">Voltar para a página inicial</a>" +
				"</body>" +
			"</html>"
		);
	}

}
