package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessEngineGUI {

	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
	}
	
	private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("ChessAI Engine");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
	
}

class MyPanel extends JPanel {

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Board
        Color color1;
        Color color2;
        int squareSize = 70;
        int startX = 50;
        int startY = 50;
        
        float[] brownHSB = Color.RGBtoHSB(102, 0, 0, null);
        Color brown = Color.getHSBColor(brownHSB[0], brownHSB[1], brownHSB[2]);
        
        float[] beigeHSB = Color.RGBtoHSB(255, 236, 209, null);
        Color beige = Color.getHSBColor(beigeHSB[0], beigeHSB[1], beigeHSB[2]);
        
        int y = startY;
        for (int i = 0; i < 8; i++) {
        	int x = startX;
        	if (i % 2 == 0) {
        		color1 = beige;
        		color2 = brown;
        	} else {
        		color1 = brown;
        		color2 = beige;
        	}
        	
        	for (int j = 0; j < 8; j++) {
        		System.out.println((j % 2));
        		if (j % 2 == 0) {
        			g.setColor(color1);
        		} else {
        			g.setColor(color2);
        		}
        		
        		g.fillRect(x, y, squareSize, squareSize);
        		x += squareSize;
        	}
        	
        	y += squareSize;
        }
        
        g.setColor(Color.black);
        g.drawRect(startX, startY, 560, 560);
        
        try {
        	
        	// Place black pieces
			Image blackBishop = ImageIO.read(getClass().getResource("img/black_bishop.png"));
			Image blackKing = ImageIO.read(getClass().getResource("img/black_king.png"));
			Image blackKnight = ImageIO.read(getClass().getResource("img/black_knight.png"));
			Image blackPawn = ImageIO.read(getClass().getResource("img/black_pawn.png"));
			Image blackRook = ImageIO.read(getClass().getResource("img/black_rook.png"));
			Image blackQueen = ImageIO.read(getClass().getResource("img/black_queen.png"));
			
			g.drawImage(blackBishop, startX + 140, startY, 70, 70, null);
	        g.drawImage(blackBishop, startX + 350, startY, 70, 70, null);
	        g.drawImage(blackKnight, startX + 70, startY, 70, 70, null);
	        g.drawImage(blackKnight, startX + 420, startY, 70, 70, null);
	        g.drawImage(blackRook, startX + 0, startY, 70, 70, null);
	        g.drawImage(blackRook, startX + 490, startY, 70, 70, null);
	        g.drawImage(blackKing, startX + 210, startY, 70, 70, null);
	        g.drawImage(blackQueen, startX + 280, startY, 70, 70, null);
	        
	        for(int i = 0; i < 8; i++) {
	        	g.drawImage(blackPawn, startX + (70 * i), startY + 70, 70, 70, null);
	        }
	        
	        // Place white pieces
	        Image whiteBishop = ImageIO.read(getClass().getResource("img/white_bishop.png"));
			Image whiteKing = ImageIO.read(getClass().getResource("img/white_king.png"));
			Image whiteKnight = ImageIO.read(getClass().getResource("img/white_knight.png"));
			Image whitePawn = ImageIO.read(getClass().getResource("img/white_pawn.png"));
			Image whiteRook = ImageIO.read(getClass().getResource("img/white_rook.png"));
			Image whiteQueen = ImageIO.read(getClass().getResource("img/white_queen.png"));
			
			g.drawImage(whiteBishop, startX + 140, startY + 490, 70, 70, null);
	        g.drawImage(whiteBishop, startX + 350, startY + 490, 70, 70, null);
	        g.drawImage(whiteKnight, startX + 70, startY + 490, 70, 70, null);
	        g.drawImage(whiteKnight, startX + 420, startY + 490, 70, 70, null);
	        g.drawImage(whiteRook, startX + 0, startY + 490, 70, 70, null);
	        g.drawImage(whiteRook, startX + 490, startY + 490, 70, 70, null);
	        g.drawImage(whiteKing, startX + 210, startY + 490, 70, 70, null);
	        g.drawImage(whiteQueen, startX + 280, startY + 490, 70, 70, null);
	        
	        for(int i = 0; i < 8; i++) {
	        	g.drawImage(whitePawn, startX + (70 * i), startY + 420, 70, 70, null);
	        }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    
    private void swapColors(Color color1, Color color2) {
    	Color temp = color1;
    	color1 = color2;
    	color2 = temp;
    }
}

