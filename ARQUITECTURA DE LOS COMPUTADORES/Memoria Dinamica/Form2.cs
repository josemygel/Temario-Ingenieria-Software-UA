using System;
using System.Windows.Forms;

namespace Dynamic_Memory
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }
        
        public string getMetodo()
        {
            return comboBox1.Text;
        }

        private void button1_Click(object sender, EventArgs e)
        {
           Enabled = false;
           Visible = false;
        }
    }
}
