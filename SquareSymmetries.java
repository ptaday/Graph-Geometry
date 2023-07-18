package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareSymmetries implements Symmetries<Square>{
    @Override
    public boolean areSymmetric(Square s1, Square s2) {

            ArrayList<Square> list= (ArrayList<Square>) symmetriesOf(s1);

            Square newSquare=(Square) s2;
            s2.toString();

            for(int i=0; i<list.size();i++)
            {
                if(list.get(i).equals(s2))
                {
                    return true;
                }
            }
            return false;


    }

    @Override
    public List<Square> symmetriesOf(Square shape) {
        ArrayList<Square> listOfSymmetries=new ArrayList<>();

        Square newSquare=shape;
       // newSquare.toString();
        listOfSymmetries.add(newSquare);

        Square square=null;

        for(int i=0;i<3;i++){
            if(i==0)
        square=shape.rotateBy(90);
            else
                square=square.rotateBy(90);

        square.toString();
        listOfSymmetries.add(square);
    }

       /* Square verticalSquare=new Square();

        System.out.println(newSquare.side4);
        verticalSquare.setSide1(newSquare.side4);
        verticalSquare.setSide2(newSquare.side3);
        verticalSquare.setSide3(newSquare.side2);
        verticalSquare.setSide4(newSquare.side1);
        System.out.println();
        System.out.println(verticalSquare);
        System.out.println();

        */



        listOfSymmetries.add(verticalReflection(newSquare));

        listOfSymmetries.add(horizontalReflection(newSquare));

        listOfSymmetries.add(leftDiagonalReflection(newSquare));

        listOfSymmetries.add(rightDiagonalReflection(newSquare));

        return listOfSymmetries;

}

public Square verticalReflection(Square square)
{
    Square newVerticalSquare=new Square(square.side4,square.side3, square.side2,square.side1);
    return newVerticalSquare;
}

    public Square horizontalReflection(Square square)
    {
        Square newHorizontalSquare=new Square(square.side2,square.side1, square.side4,square.side3);
        return newHorizontalSquare;
    }

    public Square rightDiagonalReflection(Square square)
    {
        Square newInnerSquare=new Square(square.side3,square.side2, square.side1,square.side4);
        return newInnerSquare;
    }

    public Square leftDiagonalReflection(Square square)
    {
        Square newInnerSquare=new Square(square.side1,square.side4, square.side3,square.side2);
        return newInnerSquare;
    }

    public static void main(String[] args) {

        Point  upright      = new Point("upright", 1, 1);
        Point  up           = new Point("up", 0, 1);        Point  origin       = new Point("origin", 0, 0);
        Point  right        = new Point("right", 1, 0);

        Square square=new Square(upright, up, origin, right);

        Square verticalReflection= new Square(right,origin,up,upright);

        SquareSymmetries squareSymmetries = new SquareSymmetries();
        System.out.println(squareSymmetries.areSymmetric(square, verticalReflection));

        List<Square> list=squareSymmetries.symmetriesOf(square);

        for(int i=0;i< list.size();i++)
        {
            System.out.println(list.get(i));
        }

    }

}
