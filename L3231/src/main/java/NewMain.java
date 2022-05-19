
import dao.JPaUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import metier.modele.Client;
import metier.service.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hgrel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        JPaUtil.init();
        Service service = new Service();
        service.initialiserEmployes();
        service.initialiserMediums();
        SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd");
        Client client1 = new Client("m", "abc", "def", SDFormat.parse("1970-10-21"), "0123", "abc.def@gmail.com", "abcdef");
        client1 = service.inscrireClient(client1);
        JPaUtil.destroy();
    }
    
}
