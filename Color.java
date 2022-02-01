/*
*
* Color Utility class for Data Structures
* Demonstrates class encapsulation, static finals, constructors, etc.
*
*
*
* (c) 2022 Joel A. Hammer
* Friends School of Baltimore
*
*
*
*/

/**
* Immutable class representing a computer displayable color. Colors are
* represented using the hexadecimal, rgb, and cmyk methods.
* <br><br>
* Class-defined colors match CSS usage.
*
* @author Joel Hammer
*/
public class Color {
    
    /**
    * The amount of <span style="color: red;">Red</span> in the color (a value between 0 and 255).
    * Using RGB representation.
    */
    public final int r;
    
    /**
    * The amount of <span style="color: green;">Green</span> in the color (a value between 0 and 255)
    * Using RGB representation.
    */
    public final int g;
    
     /**
    * The amount of Blue in the color (a value between 0 and 255)
    * Using RGB representation.
    */
    public final int b;
    
     /**
    * The amount of Cyan in the color (a value between 0 and 255)
    * using CMYK representation.
    */
    public final double c;
    
     /**
    * The amount of Magenta in the color (a value between 0 and 255)
    * using CMYK representation.
    */
    public final double m;
    
     /**
    * The amount of Yello in the color (a value between 0 and 255)
    * using CMYK representation.
    */
    public final double y;
    
     /**
    * The CMYK Key value of the color.
    */
    public final double k;
    
    /**
    * The hex-code representation of the color.
    */
    public final int hex;
    
    /*
    * Class-defined colors
    */
    
    /**
    * Constant representation of CSS-compatible Red.
    */
    public static final Color RED = new Color(255,0,0);
    
     /**
    * Constant representation of CSS-compatible Green.
    */
    public static final Color GREEN = new Color(0,255,0);
    
     /**
    * Constant representation of CSS-compatible Blue.
    */
    public static final Color BLUE = new Color(0,0,255);
    
     /**
    * Constant representation of CSS-compatible Brown.
    */
    public static final Color BROWN = new Color(156,42,42);
    
     /**
    * Constant representation of CSS-compatible Orange.
    */
    public static final Color ORANGE = new Color(255,165,0);
    
     /**
    * Constant representation of CSS-compatible <span style="color: purple;">Purple</span>.
    */
    public static final Color PURPLE = new Color(128,0,128);
    
     /**
    * Constant representation of CSS-compatible Yellow.
    */
    public static final Color YELLOW = new Color(255,255,0);
    
     /**
    * Constant representation of CSS-compatible Black.
    */
    public static final Color BLACK = new Color(0,0,0);
    
     /**
    * Constant representation of CSS-compatible White.
    */
    public static final Color WHITE = new Color(255,255,255);
    
    /**
    * No args constructor. Defaults to black.
    */
    public Color() {
        r = 0;
        g = 0;
        b = 0;
        
        hex = 0;
        
        c = 0;
        m = 0;
        y = 0;
        k = 1;
    }
    
    /**
    * Constructs a color of given r, g, and b values.
    * @param r the quantity of red in the color (range from 0 to 255)
    * @param g the quantity of green in the color (range from 0 to 255)
    * @param b the quantity of blue in the color (range from 0 to 255)
    */
    public Color(int r, int g, int b) throws IllegalArgumentException {
        this.r = validateChroma(r);
        this.g = validateChroma(g);
        this.b = validateChroma(b);
        
        hex = (int) Math.pow(256,2) * r + 256 * g + b;
        
        //CMYK conversion
        
        double rprime = (double) r / 255;
        double gprime = (double) g / 255;
        double bprime = (double) b / 255;
        
        this.k = getK(rprime,gprime,bprime);
        this.c = (1-rprime-k)/(1-k);
        this.m = (1-gprime-k)/(1-k);
        this.y = (1-bprime-k)/(1-k);
    }
    
    /**
    * Constructs a color of a given CMYK value.
    * @param c amount of Cyan in the color (range from 0 to 1)
    * @param m amount of Magenta in the color (range from 0 to 1)
    * @param y amount of Yello in the color (range from 0 to 1)
    * @param k amount of black (key value) in the color (range from 0 to 1)
    */
    public Color(double c, double m, double y, double k) {
        this.c = validateChroma(c);
        this.m = validateChroma(m);
        this.y = validateChroma(y);
        this.k = validateChroma(k);
        
        this.r = (int)(255 * (1 - c) * (1 - k));
        this.g = (int)(255 * (1 - m) * (1 - k));
        this.b = (int)(255 * (1 - y) * (1 - k));
        
        hex = (int) Math.pow(256,2) * r + 256 * g + b;
    }
    
    /*
    * Obtain the CMYK key value from rgb expressed as percentages (decimals from 0 to 1)
    */
    private static double getK(double rp, double gp, double bp) {
        double max = rp;
        
        if (gp > max) max = gp;
        if (bp > max) max = bp;
        
        return 1 - max;
    }
    
    /*
    * Ensure rgb values are between 0 and 255.
    */
    private static int validateChroma(int x) {
        if (x < 0 || x > 255) {
            throw new IllegalArgumentException("Color rgb values must be between 0 and 255");
        }
        return x;
    }
    
    /*
    * Ensure proper cmyk values.
    */
    private static double validateChroma(double x) {
        if (x < 0 || x > 1){
            throw new IllegalArgumentException("CMYK values must be between 0 and 1");
        }
        return x;
    }
    
    /**
    * Gives the color midway between two colors as its "mixture." Mixed
    * color is the average of the rgb values of the two colors.
    * @param x a color to mix
    * @param y a second color to mix with x
    * @return a color with the average rgb values of x and y.
    */
    public static Color mix(Color x, Color y) {
        int r = (x.r + y.r) / 2;
        int g = (x.g + y.g) / 2;
        int b = (x.b + y.b) / 2;
        
        return new Color(r, g, b);
    }
    
    /**
    * Gives the color between x and y with a given weight/ratio applied to x.
    * Higher {@code ratio} values imply greater weight towards and lower values
    * a higher weight towards y.
    * @param x a color to mix
    * @param y a second color to mix with x
    * @param ratio the proportion of x in the final mixture
    * @return a {@code Color} equivalent to the mixture of x and y with the
    * given weight applied to x.
    */
    public static Color mix(Color x, Color y, double ratio) {
        if (ratio < 0 || ratio > 1) {
            throw new IllegalArgumentException("ratio of color mixture must be between 0 and 1. Given: " + ratio);
        }
        
        double comp = 1 - ratio;
        
        int r = (int) (ratio * x.r + comp * y.r) / 2;
        int g = (int) (ratio * x.g + comp * y.g) / 2;
        int b = (int) (ratio * x.b + comp * y.b) / 2;     
        
        return new Color(r, g, b);
    }
}