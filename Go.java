

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Go extends JPanel implements MouseListener, MouseMotionListener {

    JFrame j = new JFrame();
    Graphics g = null;
    String turn = "white";
    String board[][] = new String[19][19];
    JLabel turnLbl = new JLabel();
    int x, y;    
    
    public Go() {
        
        setGUI();
        
        initBoard();
    }
    
    void initBoard() {
        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                board[i][j] = "";
            }
        }
    }

    public void paint(Graphics g) {

        g.setColor(new Color(200,150,55));
        g.fillRect(0, 0, 760, 760);
        
        g.setColor(Color.BLACK);
        for(int i=0; i<19; i++) {
            g.drawLine(0, i*40, 760, i*40);
        }
        g.drawLine(0, 759, 760, 759);
        for(int i=0; i<19; i++) {
            g.drawLine(i*40, 0, i*40, 760);
        }
        g.drawLine(759, 0, 759, 760);

        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                if(board[i][j].equals("white")) {
                    g.setColor(Color.WHITE);
                    g.fillOval(j*40, i*40, 40, 40);
                }
                else if(board[i][j].equals("black")) {
                    g.setColor(Color.BLACK);
                    g.fillOval(j*40, i*40, 40, 40);
                }
            }
        }

        if(turn.equals("white")) {
            g.setColor(Color.WHITE);
            g.drawOval(x*40-40+5, y*40-40+5, 30, 30);
            turnLbl.setText("TURN: " + turn);
        }

        if(turn.equals("black")) {
            g.setColor(Color.BLACK);
            g.drawOval(x*40-40+5, y*40-40+5, 30, 30);
            turnLbl.setText("TURN: " + turn);
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = (int)((double)e.getX()/(double)40);
        y = (int)((double)e.getY()/(double)40) - 1;
        if(turn.equals("white")) {
            g.setColor(Color.WHITE);
            g.drawOval(x*40-40+5, y*40-40+5, 30, 30);
        }
        if(turn.equals("black")) {
            g.setColor(Color.BLACK);
            g.drawOval(x*40-40+5, y*40-40+5, 30, 30);
        }
        repaint();
    }
        
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(e.getSource() instanceof JButton) {
            initBoard();
            repaint();
            turn = "white";
        } else {
            if(turn.equals("white")) {
                int x = (int)((double)e.getX()/(double)40);
                int y = (int)((double)e.getY()/(double)40) - 1;
                if(x < 20 && y < 20) {
                    if(board[y-1][x-1].equals("")) {
                        g.setColor(Color.WHITE);
                        g.fillOval(x*40-40, y*40-40, 40, 40);
                        board[y-1][x-1] = turn;
                        turn = "black";
                        turnLbl.setText("TURN: " + turn);
                    }
                }
            }
            else if(turn.equals("black")) {
                int x = (int)((double)e.getX()/(double)40);
                int y = (int)((double)e.getY()/(double)40) - 1;
                if(x < 20 && y < 20) {
                    if(board[y-1][x-1].equals("")) {
                        g.setColor(Color.BLACK);
                        g.fillOval(x*40-40, y*40-40, 40, 40);
                        board[y-1][x-1] = turn;
                        turn = "white";
                        turnLbl.setText("TURN: " + turn);
                    }
                }
            }

            try {
                figureOutWhoWon();
            } catch(Exception ee) {}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    void figureOutWhoWon() throws Exception {
        for(int j=0; j<19; j++) {
            for(int i=0; i<19; i++) {
                if(board[j][i].equals("white")) {
                    if(board[j][i+1].equals("white")) {
                        if(board[j][i+2].equals("white")) {
                            if(board[j][i+3].equals("white")) {
                                if(board[j][i+4].equals("white")) {
                                    JOptionPane.showMessageDialog(null, "WHITE wins! - 백 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("white")) {
                    if(board[j+1][i].equals("white")) {
                        if(board[j+2][i].equals("white")) {
                            if(board[j+3][i].equals("white")) {
                                if(board[j+4][i].equals("white")) {
                                    JOptionPane.showMessageDialog(null, "WHITE wins! - 백 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("white")) {
                    if(board[j+1][i-1].equals("white")) {
                        if(board[j+2][i-2].equals("white")) {
                            if(board[j+3][i-3].equals("white")) {
                                if(board[j+4][i-4].equals("white")) {
                                    JOptionPane.showMessageDialog(null, "WHITE wins! - 백 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("white")) {
                    if(board[j+1][i+1].equals("white")) {
                        if(board[j+2][i+2].equals("white")) {
                            if(board[j+3][i+3].equals("white")) {
                                if(board[j+4][i+4].equals("white")) {
                                    JOptionPane.showMessageDialog(null, "WHITE wins! - 백 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("black")) {
                    if(board[j][i+1].equals("black")) {
                        if(board[j][i+2].equals("black")) {
                            if(board[j][i+3].equals("black")) {
                                if(board[j][i+4].equals("black")) {
                                    JOptionPane.showMessageDialog(null, "BLACK wins! - 흑 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("black")) {
                    if(board[j+1][i].equals("black")) {
                        if(board[j+2][i].equals("black")) {
                            if(board[j+3][i].equals("black")) {
                                if(board[j+4][i].equals("black")) {
                                    JOptionPane.showMessageDialog(null, "BLACK wins! - 흑 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("black")) {
                    if(board[j+1][i-1].equals("black")) {
                        if(board[j+2][i-2].equals("black")) {
                            if(board[j+3][i-3].equals("black")) {
                                if(board[j+4][i-4].equals("black")) {
                                    JOptionPane.showMessageDialog(null, "BLACK wins! - 흑 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
                if(board[j][i].equals("black")) {
                    if(board[j+1][i+1].equals("black")) {
                        if(board[j+2][i+2].equals("black")) {
                            if(board[j+3][i+3].equals("black")) {
                                if(board[j+4][i+4].equals("black")) {
                                    JOptionPane.showMessageDialog(null, "BLACK wins! - 흑 이김!");
                                    initBoard();
                                    repaint();
                                    turn = "white";
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void setGUI() {
        
        j.setTitle("Go 2 - 오목 2");
        
        j.setLayout(null);
        
        j.setBounds(0, 0, 1600, 950);
        
        turnLbl.setBounds(300, 10, 300, 20);
        
        turnLbl.setText("TURN: " + turn);
        
        JPanel pp = new JPanel();
        pp.setLayout(null);
        pp.setBackground(Color.WHITE);
        pp.setBounds(j.getBounds());
        
        this.setBounds(40, 40, 760, 760);
        
        pp.add(this);
        
        pp.add(turnLbl);
        
        JButton b = new JButton("New Start. - 새로 시작");
        b.setBounds(40, 10, 163, 20);
        pp.add(b);
        b.addMouseListener(this);
        
        j.add(pp);
        
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        j.setVisible(true);

        g = getGraphics();
        
        j.addMouseListener(this);
        j.addMouseMotionListener(this);
    }
    
    public static void main(String[] args) {
        new Go();
    }
}