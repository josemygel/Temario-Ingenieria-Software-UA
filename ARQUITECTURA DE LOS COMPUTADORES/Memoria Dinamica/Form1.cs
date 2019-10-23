using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace Dynamic_Memory
{

    public partial class DynamicMemory : Form
    {
        public DynamicMemory()
        {
            InitializeComponent();

        }

        public Form2 form = new Form2();

        public class MemoryRAM
        {
            private int lineaImpresa = 0;
            private int ram = 0;
            private List<proceso> procesos;


            public MemoryRAM()
            {
                if (procesos == null)
                    procesos = new List<proceso>();
            }
            public int getRam() { return ram; }
            private void setProceso(proceso pro)
            {

                MessageBox.Show("Proceso guardado: " + pro.toString());
                pro.setSave();
                ram += pro.getMemoria();
                procesos.Add(pro);
                
            }
            public void deleteProceso(int i)
            {
                if (procesos.Count() > i && i>=0)
                {
                    MessageBox.Show("Proceso borrado: " + procesos.ElementAt(i).toString());
                    ram -= procesos.ElementAt(i).getMemoria();
                    procesos.RemoveAt(i);
                }
            }
            public void deleteProceso(proceso pro)
            {
                if (pro != null && procesos !=null) {
                    deleteProceso(procesos.IndexOf(pro));
                }
            }

            public int getIndex(proceso pro)
            {
                if (pro != null)
                    return procesos.IndexOf(pro);
                return -1;
            }
            public proceso getProceso(int i) { return procesos.ElementAt(i); }

            private proceso nextProceso(int inicio, int final=2000)
            {
                proceso pro = null;
                for (int j = 0; j < procesos.Count(); j++)
                {
                    if((procesos.ElementAt(j).getDireccionInicio() < final && procesos.ElementAt(j).getDireccionInicio()>inicio) || (procesos.ElementAt(j).getDireccionInicio() == inicio))
                    {
                        pro = procesos.ElementAt(j);
                        final = pro.getDireccionInicio();   
                    }
                }
                return pro;
            }
            public void insertarProceso(proceso pro, int inicio)
            {
                pro.setDireccionInicio(inicio);
                pro.setDireccionFinal(inicio + pro.getMemoria()-1);
                setProceso(pro);
            }

            //Analiza que un proceso pueda caber en memoria para su inserción
            public Boolean analizarProceso(proceso pro,string metodo)
            {
                if (metodo == "Siguiente Hueco")
                    return siguienteHueco(pro);
                else if (metodo == "Peor Hueco")
                    return peorhueco(pro);
                return false;
            }



            private bool peorhueco(proceso pro)         //PEOR HUECO
            {

                List<proceso> listaHuecos = new List<proceso>();
                int temporalMem = 0;

                for (int i = 0; i < 2000; i++)
                {
                    if (nextProceso(i) == null)
                    {

                        listaHuecos.Add(new proceso("hueco", "0", (2000 - i).ToString(), "0"));
                        listaHuecos.Last().setDireccionInicio(i);
                        listaHuecos.Last().setDireccionFinal(listaHuecos.Last().getMemoria() + listaHuecos.Last().getDireccionInicio());
                        i = listaHuecos.Last().getDireccionFinal();
                        break;
                    }
                    else if (nextProceso(i).getDireccionInicio() > i)
                    {
                        listaHuecos.Add(new proceso("hueco", "0", (nextProceso(i).getDireccionInicio() - i).ToString(), "0"));
                        listaHuecos.Last().setDireccionInicio(i);
                        listaHuecos.Last().setDireccionFinal(listaHuecos.Last().getMemoria() + listaHuecos.Last().getDireccionInicio());
                        i = listaHuecos.Last().getDireccionFinal() - 1;
                    }
                    else
                    {
                        i = nextProceso(i).getDireccionFinal();
                    }
                }
                
                for(int i = 0; i < listaHuecos.Count(); i++)
                {
                    if (listaHuecos.ElementAt(i).getMemoria() > temporalMem)
                    {
                        temporalMem = listaHuecos.ElementAt(i).getMemoria();
                        pro.setDireccionInicio(listaHuecos.ElementAt(i).getDireccionInicio());
                        pro.setDireccionFinal(listaHuecos.ElementAt(i).getDireccionFinal());
                    }
                }
                if (pro.getDireccionInicio() <= 1999 && pro.getDireccionFinal() >= 0)
                {
                    insertarProceso(pro,pro.getDireccionInicio());
                    return true;
                }
                else
                {

                    return false;
                }
            }


            private bool siguienteHueco(proceso pro)
            {
                if (pro.getMemoria() <= 2000)
                {

                    //A PARTIR DESDE LA POSICION INICIAL
                    if(procesos.Count>0)
                    for (int i = procesos.Last().getDireccionFinal() ; i + pro.getMemoria() <= 2000; i++)
                    {
                        for (int j = 0; procesos.Count() == 0 || j < procesos.Count(); j++) //Recorremos todos los procesos
                        {
                            if (procesos.Count() == 0)
                            {
                                insertarProceso(pro, i);
                                return true;
                            }
                            else if (procesos.ElementAt(j).getDireccionInicio() <= i && procesos.ElementAt(j).getDireccionFinal() >= i) //Encontrado proceso al inicio de la busqueda
                            {
                                i = procesos.ElementAt(j).getDireccionFinal();

                            }
                            else if (nextProceso(i) != null) //Hay un proceso desde la ubicación actual hasta la final
                            {
                                if (nextProceso(i).getDireccionInicio() - i >= pro.getMemoria()) //hay espacio!
                                {
                                    insertarProceso(pro,i);
                                    return true;
                                }
                                else //No hay espacio, así que saltamos a la ubicación final del proceso
                                {
                                    i = nextProceso(i).getDireccionFinal();
                                }
                            }
                            else if (nextProceso(i) == null) //no hay proceso desde la ubicación actual
                            {
                                if (2000 - i >= pro.getMemoria()) //Cabe en el espacio restante
                                {
                                    insertarProceso(pro, i);
                                    return true;
                                }
                                else //No cabe en el espacio restante
                                {
                                    return false;
                                }
                            }
                        }
                    }



                    //HASTA LA POSICION INICIAL
                    for (int i = 0; i + pro.getMemoria() <= 2000; i++)
                    {
                        for (int j = 0; procesos.Count() == 0 || j < procesos.Count(); j++) //Recorremos todos los procesos
                        {
                            if (procesos.Count() == 0)
                            {
                                insertarProceso(pro, i);
                                return true;
                            }
                            else if (procesos.ElementAt(j).getDireccionInicio() <= i && procesos.ElementAt(j).getDireccionFinal() > i) //Encontrado proceso al inicio de la busqueda
                            {
                                i = procesos.ElementAt(j).getDireccionFinal();

                            }
                            else if (nextProceso(i) != null) //Hay un proceso desde la ubicación actual hasta la final
                            {
                                if (nextProceso(i).getDireccionInicio() - i >= pro.getMemoria()) //hay espacio!
                                {
                                    insertarProceso(pro, i);
                                    return true;
                                }
                                else //No hay espacio, así que saltamos a la ubicación final del proceso
                                {
                                    i = nextProceso(i).getDireccionFinal();
                                }
                            }
                            else if (nextProceso(i) == null) //no hay proceso desde la ubicación actual
                            {
                                if (2000 - i >= pro.getMemoria()) //Cabe en el espacio restante
                                {
                                    insertarProceso(pro, i);
                                    return true;
                                }
                                else //No cabe en el espacio restante
                                {
                                    return false;
                                }
                            }
                        }
                    }



                    //FIN DE LOS FOR

                }
                return false;
            }

            public string toString()
            {
                List<proceso> lista = new List<proceso>();
                string texto = "";
                if (procesos.Count()>=0)
                {
                    
                    texto += (++lineaImpresa).ToString();

                    if (lineaImpresa < 10)
                        texto += "   ";
                    else if (lineaImpresa < 100)
                        texto += "  ";
                    else
                        texto += " ";

                    for (int i = 0; i < 2000; i++)
                    {
                        if (nextProceso(i) == null)
                        {
                            
                            lista.Add(new proceso("hueco", "0", (2000-i).ToString(), "0"));
                            lista.Last().setDireccionInicio(i);
                            lista.Last().setDireccionFinal(lista.Last().getMemoria()+lista.Last().getDireccionInicio());
                            i = lista.Last().getDireccionFinal();
                            break;
                        }
                        else if (nextProceso(i).getDireccionInicio() > i)
                        {
                            lista.Add(new proceso("hueco", "0", (nextProceso(i).getDireccionInicio()-i).ToString(), "0"));
                            lista.Last().setDireccionInicio(i);
                            lista.Last().setDireccionFinal(lista.Last().getMemoria() + lista.Last().getDireccionInicio());
                            i = lista.Last().getDireccionFinal()-1;
                        }
                        else
                        {
                            lista.Add(nextProceso(i));
                            i = nextProceso(i).getDireccionFinal();
                        }
                    }

                    for(int i = 0; i < lista.Count(); i++)
                    {
                        texto += "     ";
                        texto += lista.ElementAt(i).toString();
                    }

                    lista.Clear();

                    texto += "\n";
                }
                return texto;
            }
        }

        public class proceso
        {
            private string nombre;
            private int llegada;
            private int memoria;
            private int tiempo;
            private int direccionInicio; //Fuera de memoria
            private int direccionFinal; //Fuera de memoria

            public bool save = false;

            public void setSave() { save = true; }
            public bool getSave() { return save; }
            public proceso(string nombre,string llegada, string memoria, string tiempo)
            {
                setNombre(nombre);
                setLlegada(llegada);
                setMemoria(memoria);
                setTiempo(tiempo);
            }

            public void setNombre(string nombre) { this.nombre = nombre; }
            public void setLlegada(string llegada) { this.llegada = int.Parse(llegada); }
            public void setMemoria(string memoria) { this.memoria = int.Parse(memoria); }
            public void setTiempo(string tiempo) { this.tiempo = int.Parse(tiempo); }
            public string getNombre() { return nombre; }
            public int getLlegada() { return llegada; }
            public int getMemoria() { return memoria; }
            public int getTiempo() { return tiempo; }


            public void setDireccionInicio(int tiempoInicio) { direccionInicio = tiempoInicio; }
            public int getDireccionInicio() { return direccionInicio; }
            public void setDireccionFinal(int tiempoFinal) { direccionFinal = tiempoFinal; }
            public int getDireccionFinal() { return direccionFinal; }

            public string toString()
            { //MAL MOSTRADO
                return "[" + getDireccionInicio() + " " + getNombre() + " " + getMemoria() + "]";
            }
        }

        public void actualizar(MemoryRAM memoria)
        {
            richTextBox1.Text += memoria.toString();
            progressBar1.Value = memoria.getRam();
        }
        private void leer(List<proceso> procesos)
        {
            //Abrimos el archivo seleccionado
            System.IO.StreamReader file = new System.IO.StreamReader(openFileDialog1.OpenFile());
            openFileDialog1.OpenFile().Close();
            string line;

            while (!file.EndOfStream && (line = file.ReadLine()) != null)
            {
                string[] substrings = line.Split(' ');

                if (int.Parse(substrings[2]) <= 2000)
                {
                    proceso proc = new proceso(substrings[0], substrings[1], substrings[2], substrings[3]);

                    procesos.Add(proc);
                }
                
            }
        }


        private void finalizaElemento(ref MemoryRAM memoria, List<proceso> procesos, int i, int j)
        {
            if (procesos.ElementAt(j).getLlegada() + procesos.ElementAt(j).getTiempo() <= i)
            {
                memoria.deleteProceso(procesos.ElementAt(j)); //Borrado del proceso en la memoria
                procesos.RemoveAt(j); //Borrado del proceso de la lista
                actualizar(memoria);

            }
        }
        public void main()
        {
            MemoryRAM memoria = new MemoryRAM();
            List<proceso> procesos = new List<proceso>();
            
            //Lectura de los procesos leidos
            leer(procesos);
            
            //Se procede a su insercion en memoria
            for(int i=0; i<int.MaxValue && procesos.Count()>0; i++) //Analiza el tiempo siempre y cuando existan elementos que inspeccionar
            {
                procesador(ref memoria, procesos, i);
            }
        }

        private void procesador(ref MemoryRAM memoria, List<proceso> procesos, int i)
        {
            for (int j = 0; j < procesos.Count(); j++)
            {

                //Comprobamos que el proceso finaliza
                finalizaElemento(ref memoria, procesos, i, j);

                //ignoramos o introducimos elementos con tiempo de inicio "i"
                //sincronizacionDeProcesos(ref memoria, procesos, ref textShow, ref ram, ref contador, j, i);


                if (j<procesos.Count() && !procesos.ElementAt(j).getSave() && procesos.ElementAt(j).getLlegada() <= i)
                {
                    if (procesos.ElementAt(j).getMemoria() + memoria.getRam() <= 2000)
                    {
                        if(memoria.analizarProceso(procesos.ElementAt(j),form.getMetodo()))
                            actualizar(memoria); //insertamos el proceso
                        //MessageBox.Show("Veamos: " + memoria.getProceso(0).toString() + memoria.getProceso(1).toString());
                    }
                    else
                    {
                        //Este proceso no cabe, por lo que es eliminado e ignorado.
                        //MessageBox.Show("Proceso omitido: " + procesos.ElementAt(j).toString());
                        procesos.ElementAt(j).setLlegada((procesos.ElementAt(j).getLlegada()+1).ToString());
                    }
                }
            }
        }
        
        private void Form1_Load(object sender, EventArgs e)
        {
            form.ShowDialog();
            if (form.getMetodo()!="Siguiente Hueco" && form.getMetodo()!="Peor Hueco")
            {
                Close();
            }
            openFileDialog1.ShowDialog();

            try {
                if (openFileDialog1.OpenFile().CanRead)
                {
                    Enabled = true;
                    WindowState = FormWindowState.Normal;
                    main();
                }

            }
            catch (System.IO.FileNotFoundException)
            {
                 Close();
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Derechos reservados por Jose Miguel Gómez Lozano, estudiante de la UA");
        }

        private void richTextBox1_DoubleClick(object sender, EventArgs e)
        {
            //Abrimos el archivo seleccionado
            
            string texto = richTextBox1.Text;

            SaveFileDialog save = saveFileDialog1;

            save.FileName = "particiones.txt";
            save.Filter = "Archivos de Texto .txt | *.txt";

            if (save.ShowDialog() == DialogResult.OK)
            {
                System.IO.StreamWriter writer = new System.IO.StreamWriter(save.OpenFile());
                
                writer.WriteLine(richTextBox1.Text);
                
                writer.Dispose();
                writer.Close();

            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String help = "INSTRUCCIONES:\n";

            help += "\n-Si clickas 1 vez la imagen de RAM aparecerán los créditos.\n";
            help += "\n-Si clickas 2 veces el texto mostrado el programa te ofrecerá\n";
            help += "   un formulario nuevo para elegir donde guardar la salida.\n";
            help += "\n-La barra inferior representa el total de memoria RAM consumida\n";
            help += "   por los procesos que se están ejecutando actualmente.\n";
            help += "\n-Los mensajes muestran las entradas o salidas de los procesos\n";
            help += "   para apreciar el estado de la RAM paso por paso.\n";
            help += "\n-Para mayor comodidad puede agrandar " + this.Name.ToString() +".\n";
            help += "\n-NOTA: El bloc de notas no muestra los saltos de linea.";


            MessageBox.Show(help);
        }
    }
}