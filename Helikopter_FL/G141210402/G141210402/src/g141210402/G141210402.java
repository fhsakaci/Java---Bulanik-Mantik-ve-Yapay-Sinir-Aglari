package g141210402;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner; 
import java.util.Random;
import static javafx.application.Platform.exit;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
/**
 *
 * @author SAKACI
 */
public class G141210402 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file = new File("dosya.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        for(int i=0;i<10000;i++)
        {
            Random r=new Random(); //random sınıfı
        double k =r.nextDouble()*12-5;
        double t =r.nextDouble()*20000;
        double n =r.nextDouble()*5000;
        try{
                Helikopter c=new Helikopter(k,t,n);
                //System.out.println(k+" "+t+" "+n+" "+c);
                bWriter.write(k/12+" "+t/20000+" "+n/5000+" "+c);
                bWriter.newLine();
        }
        catch(URISyntaxException ex)
        {
            System.out.print("HATA");
        }
        }
        bWriter.close();
        
    }
    
}
