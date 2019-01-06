/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g141210402;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author SAKACI
 */
public class G141210402 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean egitim=false;
        YSA ysa = new YSA(150);
        Scanner in = new Scanner(System.in);
        int sec = 0;
        do{
            System.out.println("1. Agi Egit");
            System.out.println("2. Agi Test Et");
            System.out.println("3. Deger Test Et");
            System.out.println("4. Cikis");
            sec = in.nextInt();
            switch(sec){
                case 1:
                    ysa.egit();
                    System.out.println("Hata Orani: "+ysa.hata());
                    egitim=true;
                    break;
                case 2:
                    if(egitim==false)
                    {
                        ysa.egit();
                        System.out.println("Hata Orani: "+ysa.hata());
                    }
                    ysa.test();
                    break;
                case 3:
                    System.out.print("x:");
                    double x = in.nextDouble();
                    System.out.print("y:");
                    double y = in.nextDouble();
                    System.out.print("z:");
                    double z = in.nextDouble();
                    double []cikti = ysa.ManualTest(x/12,y/20000,z/5000);
                    System.out.println("Uçuş Durumu: %"+cikti[0]*100);
            }
        }while(sec != 4);
    }
    
}
