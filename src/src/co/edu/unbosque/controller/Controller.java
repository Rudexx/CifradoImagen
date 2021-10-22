package src.co.edu.unbosque.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;




import src.co.edu.unbosque.view.View;


public class Controller {
	
	private View v;

    public  Controller() {
    	v = new View();
        run();
    }
    public void run(){
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("Seleccione una imagen");
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
    	 FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP & PNG", "jpg", "gif", "bmp", "png");
    	 fileChooser.setFileFilter(filtroImagen);
    	
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects a file
			File selectedFile = fileChooser.getSelectedFile();
			
			try {
				 BufferedImage img = ImageIO.read(selectedFile);
				 int[][] pixeles = new int[img.getWidth()][img.getHeight()];

			      for (int y = 0; y < img.getHeight(); y++) {
			          for (int x = 0; x < img.getWidth(); x++) {
			             int pixel = img.getRGB(x,y);
			             int b = (pixel)&0xFF;
			             int g = (pixel>>8)&0xFF;
			             int r = (pixel>>16)&0xFF;
			             pixeles[x][y] = r;
			          }
			       }
			      int[][] cifradoN = cifrarMatriz(pixeles,img.getWidth(), img.getHeight());
			      int xLenght = img.getHeight();
			      int yLength = img.getWidth();
			      BufferedImage b = new BufferedImage(xLenght, yLength, 3);

			      for(int x = 0; x < xLenght; x++) {
			          for(int y = 0; y < yLength; y++) {
			              int rgb = (int)cifradoN[x][y]<<16 | (int)cifradoN[x][y] << 8 | (int)cifradoN[x][y];
			              b.setRGB(x, y, rgb);
			          }
			      }
			      File myObj = new File(System.getProperty("user.home") + "/Desktop/nuevo.jpg");
			      ImageIO.write(b, "Doublearray", myObj);
			      System.out.println("end");
			        
			        
			    
			    
			} catch (IOException e) {
				e.printStackTrace();
				v.mostrarInformacion("El archivo no es una imagen");
			}
		
		}else {
			System.exit(0);
		}
    }
    
    public int[][] cifrarMatriz(int[][] p,int width, int height){
    	int[][] cifrado = new int[height][width];

    	for (int i = 0; i < width; i++) {
			for (int j = 0; j <height; j++) {
				if( i < width && i< height && j < width && j< height) {
				if(i == 0) {
					cifrado[i][j] = p[height-2][j] % 256;
				}else if(i == 1) {
					cifrado[i][j] = p[height-1][j] % 256;
				}else {
					cifrado[i][j] =p[i-2][j] % 256;
				}
				}
			}
		}
    	return cifrado;
    }
    
    
    public int[] convertToArray(int[][] matrix, int width, int height) {
    	
    	int newArray[] = new int[matrix.length*matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
        	int[] row = matrix[i];
            for(int j = 0; j < row.length; j++) {
            	int number = matrix[i][j];
                newArray[i*row.length+j] = number;
            }
        }
        
        for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}

    	return newArray;
    	
    }
    	
}

