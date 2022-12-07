public interface Convert {
    public static int convertY(int y)
    {
        return 56-y;
    }
    public static int convertX(char x)
    {
        x = Character.toLowerCase(x);
        int numX = 0;
        if (x == 'a'){
            numX = 0;
        }
        else if (x == 'b'){
            numX = 1;
        }
        else if (x == 'c'){
            numX = 2;
        }
        else if (x == 'd'){
            numX = 3;
        }
        else if (x == 'e'){
            numX = 4;
        }
        else if (x == 'f'){
            numX = 5;
        }
        else if (x == 'g'){
            numX = 6;
        }
        else if (x == 'h'){
            numX = 7;
        }
//        return x-65;
        return numX;
    }
}
