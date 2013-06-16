package rusyk.charts;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
    private static final int MAX_SCORE = 50;
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(0.3f);
    private static final int GRAPH_POINT_WIDTH = 5;
    private static final int Y_HATCH_CNT = 10;
    private String[][] scores;
    private Integer currentPointIndex;
    private Integer currentPointPosX;
    private Integer currentPointPosY;

    public DrawGraph(final String[][] scores) {
        this.scores = scores;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < scores[0].length; i++) {
                        currentPointIndex = i;
                        repaint();
                        try {
                            //set repaint frequency
                            //Thread.sleep(0L, 1);
                            Thread.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }


                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        draw(g);
    }

    private void draw(Graphics g) {
        Integer index = currentPointIndex;

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Stroke oldStroke = g2.getStroke();
        final int thickness = 4;
        g2.setStroke(new BasicStroke(thickness));

        //graphics2D.drawLine(x1, y1, x2, y2);
        g2.setStroke(oldStroke);

        Double maxX = -1d;
        for (int i=0; i < scores[0].length; i++) {
            if (Double.valueOf(scores[0][i].trim()) > maxX) {
                maxX = Double.valueOf(scores[0][i].trim());
            }
        }


        Double maxY = -1d;
        for (int i=0; i < scores[1].length; i++) {

            if (Double.valueOf(scores[1][i].trim()) > maxY) {
                maxY = Double.valueOf(scores[1][i].trim());
            }
        }

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (maxX);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (maxY);


        List<Point> graphPoints = new ArrayList<Point>();
        Double lastY = new Double(0);
        for (int i = 0; i < scores[0].length; i++) {

            int x1 = (int) ((Double.valueOf(scores[0][i].trim()) * xScale) + BORDER_GAP);

            /*if (Double.valueOf(scores[0][i].trim()) > Double.valueOf(scores[0][i + 1].trim())) {
                lastY = Double.valueOf(scores[1][i].trim());
            }*/

            int y1 = (int) (((Double.valueOf(scores[1][i].trim())+lastY) * yScale) + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }


        /*   X AND Y AXES AND HATCHETS
        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, BORDER_GAP, getWidth() - BORDER_GAP, BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < maxY; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = (int) ((i * (getHeight() - BORDER_GAP * 2)) / maxY + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < maxX - 1; i++) {
            int x0 = (int) ((i + 1) * (getWidth() - BORDER_GAP * 2) / (maxX - 1) + BORDER_GAP);
            int x1 = x0;
            int y0 = BORDER_GAP;
            int y1 = y0 + GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }
        */


        //draw lines
        oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }


        //draw "animated" points
        if (index != null) {

            Color color = g2.getColor();


            g2.setColor(Color.BLACK);
            currentPointPosX = graphPoints.get(index-1).x;
            currentPointPosY = graphPoints.get(index-1).y;
            g2.drawOval(currentPointPosX-10, currentPointPosY - 10, 20, 20);
            g2.fillOval(currentPointPosX-10, currentPointPosY - 10, 20, 20);

            currentPointPosX = graphPoints.get(index).x;
            currentPointPosY = graphPoints.get(index).y;
            g2.drawOval(currentPointPosX-10, currentPointPosY - 10, 20, 20);
            g2.fillOval(currentPointPosX-10, currentPointPosY - 10, 20, 20);

            currentPointPosX = graphPoints.get(index+1).x;
            currentPointPosY = graphPoints.get(index+1).y;
            g2.drawOval(currentPointPosX-10, currentPointPosY - 10, 20, 20);
            g2.fillOval(currentPointPosX-10, currentPointPosY - 10, 20, 20);

            g2.setColor(color);
        }


        /* GRAPH POINTS
        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            //g2.fillOval(x, y, ovalW, ovalH);
            g2.fillOval(x, y, 0, 0);
            //moveTo(graphPoints.get(i+1),graphPoints.get(i+1));
        }
        */
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGui() {
        /*List<Integer> scores = new ArrayList<Integer>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints ; i++) {
            scores.add(random.nextInt(maxScore));
        }
        DrawGraph mainPanel = new DrawGraph(scores);

        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);*/
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
*/
}