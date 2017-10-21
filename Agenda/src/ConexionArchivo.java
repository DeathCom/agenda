
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConexionArchivo {
    
    
    private void CrearArchivo() throws IOException{
        File nuevoArchivo =  new File("ListaAgenda.txt");//ruta del archivo
        if(!nuevoArchivo.exists()){
            try (FileWriter CrearArchivo = new FileWriter(nuevoArchivo) //nombre del archivo
            ) {
                CrearArchivo.write("");
            }
        }
        
    }
    public void GuardarLista(ArrayList <Contacto> ListaContactos) throws IOException{
        Contacto obTemp = new Contacto();
        String textoSalida = " ";
        
        File NuevoArchivo = new File("ListaAgenda.txt");
        
         if(!NuevoArchivo.exists()){
            CrearArchivo();
        }
         
        try (FileWriter EscribirAchivo = new FileWriter(NuevoArchivo); 
                BufferedWriter Guardar = new BufferedWriter(EscribirAchivo)) {
            
            for(int i=0; i<ListaContactos.size(); i++){
                obTemp = ListaContactos.get(i);
                textoSalida = textoSalida+obTemp.getNombre() + ";";
                textoSalida = textoSalida+obTemp.getApellidos() + ";";
                textoSalida = textoSalida+obTemp.getTelefono() + "\n";
            }
            Guardar.write(textoSalida);
        }
    }
    public ArrayList <Contacto> obtenerLista(){
        ArrayList <Contacto> ListaTemp = new ArrayList <Contacto>();
        String texto = "";
        
        
        try {
            File lectura = new File("ListaAgenda.txt");
            if(!lectura.exists()){ 
                CrearArchivo(); 
            }
            FileReader leer = new FileReader(lectura);
            BufferedReader buffer = new BufferedReader(leer);//buffer de lectura
            String temp;
            while((temp=buffer.readLine())!=null){
                String[] campo = temp.split(";");
                Contacto contacto =new Contacto();

                contacto.setNombre(campo[0]);
                contacto.setApellidos(campo[1]);
                contacto.setTelefono(campo[2]);

                ListaTemp.add(contacto);
            }
                buffer.close();     
                leer.close();//cierre de archivo
        } catch (Exception e) { // log de errors
            
        } 
        return ListaTemp;
    }
    
    
}
