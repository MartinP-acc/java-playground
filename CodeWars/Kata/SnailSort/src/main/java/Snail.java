public class Snail {

    public static int[] snail(int[][] array) {

        if (array[0].length==0) return new int[0];

        int len             = array.length;
        int row             = 0;
        int col             = 0;
        int direction       = 0;
        int counter         = 0;
        int[] snailSorted   = new int[len*len];

        for (int i=0; i<snailSorted.length; i++){

            snailSorted[i] = array[row][col];

            if (counter==len-1) {
                direction++;
                if (direction==3) len--;
                if (counter<i && direction==1) len--;
                counter=0;
            }
            counter++;

            if (direction>3) direction=0;

            switch (direction){
                case 0 : col++; break;
                case 1 : row++; break;
                case 2 : col--; break;
                case 3 : row--; break;
            }

        }
        return snailSorted;
    }
}
