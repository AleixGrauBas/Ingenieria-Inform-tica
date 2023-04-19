package cliente;


public class TestAgenda {

	public static void main(String[] args) {

		GestorContactos gestor = new GestorContactos();
		String cadena = "";
		boolean res;
		try {
			gestor.nuevoContacto("Pedro", "666666666");
			System.out.println("Creado nuevo contacto: Pedro#666666666");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			cadena = gestor.getContacto(1);
			if (!cadena.isEmpty())
				System.out.println("Contacto con id 1: " + cadena);
			else
				System.out.println("Contacto con id 1 no encontrado");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			gestor.nuevoContacto("Ana", "612345678");
			System.out.println("Creado nuevo contacto: Ana#612345678");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			res = gestor.actualizaContacto(1, "Pedro", "666778899");
			if (res)
				System.out.println("Actualizado contacto con id 1: Pedro#666778899");
			else
				System.out.println("Contacto a actualizar con id 1 no encontrado");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			cadena = gestor.getContacto(1);
			if (!cadena.isEmpty())
				System.out.println("Contacto con id 1: " + cadena);
			else
				System.out.println("Contacto con id 1 no encontrado");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			cadena = gestor.getContacto(2);
			if (!cadena.isEmpty())
				System.out.println("Contacto con id 2: " + cadena);
			else
				System.out.println("Contacto con id 2 no encontrado");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			res = gestor.borraContacto(1);
			if (res)
				System.out.println("Borrado contacto con id: 1");
			else
				System.out.println("Contacto a borrar con id 1 no encontrado");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			res = gestor.borraContacto(3);
			if (res)
				System.out.println("Borrado contacto con id: 3");
			else
				System.out.println("Contacto a borrar con id 3 no encontrado");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
