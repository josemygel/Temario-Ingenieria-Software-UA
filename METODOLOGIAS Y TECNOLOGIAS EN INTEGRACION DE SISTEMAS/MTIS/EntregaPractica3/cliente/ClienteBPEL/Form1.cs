using System;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteBPEL
{
    public partial class Form1 : Form
    {

        private AlmacenBPEL.AlmacenBPELPortTypeClient client;

        public Form1()
        {
            this.client = new AlmacenBPEL.AlmacenBPELPortTypeClient();

            InitializeComponent();
        }
        private async Task realizarcompra(string referencia, int cantidad)
        {
            AlmacenBPEL.comprarRequest comprarRequest = new AlmacenBPEL.comprarRequest();
            //            AlmacenBPEL.AlmacenBPELRequest almacenRequest = new AlmacenBPEL.AlmacenBPELRequest();
            comprarRequest.AlmacenBPELRequest = new AlmacenBPEL.AlmacenBPELRequest();

            comprarRequest.AlmacenBPELRequest.referencia = referencia;
            comprarRequest.AlmacenBPELRequest.unidades = cantidad;

            AlmacenBPEL.comprarResponse response = await client.comprarAsync(comprarRequest.AlmacenBPELRequest);
            textBox2.Text = response.AlmacenBPELResponse.resultado.Replace(" || ", "\r\n\r\n");
        }
        private async void button1_ClickAsync(object sender, EventArgs e)
        {
            int cantidad = int.Parse(numericUpDown1.Value.ToString());
            string referencia = textBox1.Text;
            try
            {
                await realizarcompra(referencia, cantidad);
            }
            catch (ArgumentNullException)
            {
                textBox2.Text = "ArgumentNullException";
            }

        }
    }
}
