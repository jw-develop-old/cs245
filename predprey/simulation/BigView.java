package simulation;

import java.awt.*;

public class BigView extends Component implements View {

   private static final long serialVersionUID = -3298488245460088627L;

    private int uWidth, uHeight, uSize;

    private Model model; 

    public BigView(int uWidth, int uHeight, int uSize, Model model) {
        super();   // just in case
        this.model = model;
        this.uWidth = uWidth;
        this.uHeight = uHeight;
        this.uSize = uSize;
        setSize(uWidth * uSize, uHeight * uSize);
        setPreferredSize(new Dimension(uWidth * uSize, uHeight * uSize));
    }

    public void display() { repaint(); }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < uWidth; i++)
            for (int j = 0; j < uHeight; j++) {
                g.setColor(model.getColorAt(i, j));
                g.fillRect(i * uSize, (uHeight - j - 1) * uSize, uSize, uSize);
            }
    }

}
