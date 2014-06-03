package cl.dcc;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Gonzaloafa on 31-05-2014.
 */



public class View extends JPanel {

    private static final int width = 500 , height = 500;

    static public void main(String[]args){

        View panel = new View();
        panel.setPreferredSize(new Dimension(width, height));

        final JFrame frame = new JFrame("KDTreeTest");

        frame.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints cnstrs = new GridBagConstraints();
        cnstrs.fill = GridBagConstraints.BOTH;
        cnstrs.gridx = GridBagConstraints.REMAINDER;
        cnstrs.gridy = GridBagConstraints.REMAINDER;

        cnstrs.weightx = 1.0;
        cnstrs.weighty = 1.0;
        frame.getContentPane().add( panel , cnstrs );

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        frame.pack();
        frame.setVisible( true );



        GeneratePoint point = new LowDiscrepancyGenerate(0, 500);
        List<KDPoint> kdPoints = point.generate(3000);

        frame.add(new PointsView(kdPoints));

    }




    static class PointsView extends Canvas {

        List<KDPoint> points;

        public PointsView ( List<KDPoint> points ) {
            this.points = points;
            setSize(width,height);
        }

        public void paint (Graphics g) {

            KDPoint point;

            for (int i = 0; i < points.size() ; i++) {
                point =  points.get(i);
                g.fillOval( (int)point.getX(), (int)point.getY(), 3, 3);
            }
        }
    }


    class KDNodeView extends Canvas {

        public KDNodeView() {
            setSize(width,height);
        }
        public void paint (Graphics g) {
        }
    }


}
