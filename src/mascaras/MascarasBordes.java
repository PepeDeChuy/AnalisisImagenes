package mascaras;

public class MascarasBordes 
{
    // kirch
    public static int[][] kirsch1 = {
                                        {0, 0, 0, 0, 0},
                                        {0,-3, -3, 5, 0}, 
                                        {0,-3, 0, 5, 0}, 
                                        {0,-3, -3, 5, 0},
                                        {0, 0, 0, 0, 0}
                                    };
    
    public static int[][] kirsch2 = {{0, 0, 0, 0, 0},{0,-3, 5, 5,0},{0,-3, 0, 5,0},{0,-3, -3, -3,0},{0, 0, 0, 0, 0}};
    
    public static int[][] kirsch3 = {{0, 0, 0, 0, 0},{0,5, 5, 5,0}, {0,-3, 0, -3,0}, {0,-3, -3, -3,0},{0, 0, 0, 0, 0}};
    
    public static int[][] kirsch4 = {{0, 0, 0, 0, 0},{0,5, 5, -3,0}, {0,5, 0, -3,0}, {0,-3, -3, -3,0},{0, 0, 0, 0, 0}};
    
    public static int[][] kirsch5 = {{0, 0, 0, 0, 0},{0,5, -3, -3,0}, {0,5, 0, -3,0}, {0,5, -3, -3,0},{0, 0, 0, 0, 0}};
    
    public static int[][] kirsch6 = {{0, 0, 0, 0, 0},{0,-3, -3, -3,0}, {0,5, 0, -3,0}, {0,5, 5, -3,0},{0, 0, 0, 0, 0}};
    
    public static int[][] kirsch7 = {{0, 0, 0, 0, 0},{0,-3, -3, -3,0}, {0,-3, 0, -3,0}, {0,5, 5, 5,0},{0, 0, 0, 0, 0}};
    
    public static int[][] kirsch8 = {{0, 0, 0, 0, 0},{0,-3, -3, -3,0}, {0,-3, 0, 5,0}, {0,-3, 5, 5,0},{0, 0, 0, 0, 0}};
    
    public static int[][][] arregloMascaras = 
    {
        kirsch1, kirsch2, kirsch3,
        kirsch4, kirsch5, kirsch6,
        kirsch7, kirsch8
    };
   
    
}
