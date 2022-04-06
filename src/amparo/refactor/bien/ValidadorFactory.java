package amparo.refactor.bien;



class ValidadorFactory 
{
	public static IValidadorDocumento getValidador(String doc)
	{
		IValidadorDocumento miValidador = null;


		if (isNie(doc)){
			miValidador=new ValidadorNIE(doc);
		}else if (isDni(doc)){
			miValidador = new ValidadorDNI(doc);
		}
		 return miValidador;
	}


	private static boolean isDni(String doc){

		 if(doc.length() == 9 && Character.isLetter(doc.charAt(8))) {

			return true;
		}
		 return false;
	}

	private static boolean isNie(String doc){

		if(doc.length() == 9 && Character.isLetter(doc.charAt(8))
			&& (Character.toUpperCase(doc.charAt(0)) == 'X'
			|| 	Character.toUpperCase(doc.charAt(0)) == 'Y'
			|| 	Character.toUpperCase(doc.charAt(0)) == 'Z')) {

			return true;
		}
		return false;
	}
}


