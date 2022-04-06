package amparo.refactor.bien;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCif implements IValidadorDocumento {

	private String cif = null;
	
	public ValidadorCif(String cif)
	{
		this.cif= cif;
	}
// se que tenia que haber partido este validar en funciones mas pequeñas pero de momento se queda asi
	public boolean validar() {
		if (this.cif != null) {
			final String cifUP = this.cif.toUpperCase();


			if ("ABCDEFGHJKLMNPQRSUVW".indexOf(cifUP.charAt(0)) == -1) {
				return false;
			}


			final Pattern mask = Pattern
					.compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
			final Matcher matcher = mask.matcher(cifUP);
			if (!matcher.matches()) {
				return false;
			}

			final char primerCar = cifUP.charAt(0);
			final char ultimoCar = cifUP.charAt(cifUP.length() - 1);


			ultimoCaracter tipoUltimoCaracter;


			if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W') {
				tipoUltimoCaracter = ultimoCaracter.LETRA;
				if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
					return false;
				}

			} else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E'
					|| primerCar == 'H') {
				tipoUltimoCaracter = ultimoCaracter.NUMERO;
				if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
					return false;
				}
				} else {
				tipoUltimoCaracter = ultimoCaracter.AMBOS;
			}


			final String digitos = cifUP.substring(1, cifUP.length() - 1);

			// sumo los pares
			Integer sumaPares = 0;
			for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
				sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
			}

			// sumo los impares
			Integer sumaImpares = 0;
			for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
				Integer cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
				if (cal.toString().length() > 1) {
					cal = Integer.parseInt(cal.toString().substring(0, 1))
							+ Integer.parseInt(cal.toString().substring(1, 2));
				}
				sumaImpares += cal;
			}


			final Integer total = sumaPares + sumaImpares;


			Integer numControl = 10 - (total % 10);


			int pos = numControl == 10? 0:numControl;
			final char carControl = "JABCDEFGHI".charAt(pos);


			if (tipoUltimoCaracter == ultimoCaracter.NUMERO) {

				final Integer ultCar = Integer.parseInt(Character
						.toString(ultimoCar));
				if (pos != ultCar.intValue()) {

					return false;
				}

			} else if (tipoUltimoCaracter == ultimoCaracter.LETRA) {
				if (carControl != ultimoCar) {
					return false;
				}

			} else {

				Integer ultCar = -1;

				ultCar = "JABCDEFGHI".indexOf(ultimoCar);

				if (ultCar < 0){
					ultCar = Integer.parseInt(Character.toString(ultimoCar));
					if (pos != ultCar.intValue()) {
						return false;
					}
				}
				if ((pos != ultCar.intValue()) && (carControl != ultimoCar)) {
					return false;
				}
			}
			return true;
		}
		return false;

	}


}



