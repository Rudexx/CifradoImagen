package src.co.edu.unbosque.view;

import javax.swing.*;

public class View {



    public View() {
    	try {
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    public void mostrarInformacion(String mensages){
        JOptionPane.showMessageDialog(null, mensages );
    }

    public String ingresarInformacion(String mensage){
        return JOptionPane.showInputDialog(mensage);
    }
    
    

    public Integer mostrarOpcion(String mensaje, String op1, String op2) {
        Object[] options = { op1, op2};
       return JOptionPane.showOptionDialog(null, "Tipo de metodo", mensaje,
        	    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
        	    options, options[0]);
        
    }
    public void mostrarInformacionConsola(String mensage){
        System.out.print(mensage);
    }


}
