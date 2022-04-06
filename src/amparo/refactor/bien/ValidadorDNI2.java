package amparo.refactor.bien;

public class ValidadorDNI2 implements IValidadorDocumento {

	private String dni = null;

	public ValidadorDNI2(String dni)
	{
		this.dni = dni;
	}

	public boolean validar() {

		String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";

		String numerosDni = this.dni.trim().replaceAll(" ", "").substring(0, 8);

		char letraDni = this.dni.charAt(8);
		int valNumDni = Integer.parseInt(numerosDni) % 23;


		if (this.dni.length() != 9 || isNumeric(numerosDni) == false || dniChars.charAt(valNumDni) !=  letraDni) {
			return false;
		} else {
			return true;
		}
	}
		public static boolean isNumeric (String strNum) {
			if (strNum == null) {
				return false;
			}
			try {
				double d = Double.parseDouble(strNum);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}


	}




