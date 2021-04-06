using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Teatrus.GUI;

namespace Teatrus.UserControls
{
    public partial class UserControlRegistration : UserControl
    {
        public UserControlRegistration()
        {
            InitializeComponent();
        }

        private void lblLogin_Click(object sender, EventArgs e)
        {
            if (LoginRegistrationWindow.Instance.PanelContainer.Controls["UserControlLogin"].Controls.Count == 0)
            {
                LoginRegistrationWindow.Instance.PanelContainer.Controls.Clear();
                UserControlLogin userControlLogin = new UserControlLogin();
                userControlLogin.Dock = DockStyle.Fill;
                LoginRegistrationWindow.Instance.PanelContainer.Controls.Add(userControlLogin);
            }
            
            LoginRegistrationWindow.Instance.PanelContainer.Controls["UserControlLogin"].BringToFront();
           
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            this.txtUsername.Text = "";
            this.txtPassword.Text = "";
            this.txtConfirmPassword.Text = "";
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void checkBoxShowPassword_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBoxShowPassword.Checked)
            {
                this.txtPassword.UseSystemPasswordChar = false;
                this.txtConfirmPassword.UseSystemPasswordChar = false;
            }
            else
            {
                this.txtPassword.UseSystemPasswordChar = true;
                this.txtConfirmPassword.UseSystemPasswordChar = true;
            }
        }
    }
}
