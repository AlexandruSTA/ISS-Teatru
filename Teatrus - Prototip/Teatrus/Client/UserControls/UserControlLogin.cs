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
    public partial class UserControlLogin : UserControl
    {
        public UserControlLogin()
        {
            InitializeComponent();
        }

        private void lblCreateAccount_Click(object sender, EventArgs e)
        {
            if (LoginRegistrationWindow.Instance.PanelContainer.Controls["UserControlRegistration"]== null)
            {                
                UserControlRegistration userControlRegistration = new UserControlRegistration();
                userControlRegistration.Dock = DockStyle.Fill;
                userControlRegistration.MouseMove += new System.Windows.Forms.MouseEventHandler(LoginRegistrationWindow.Instance.LoginRegistrationWindow_MouseMove);
                userControlRegistration.MouseUp += new System.Windows.Forms.MouseEventHandler(LoginRegistrationWindow.Instance.LoginRegistrationWindow_MouseUp);
                userControlRegistration.MouseDown += new System.Windows.Forms.MouseEventHandler(LoginRegistrationWindow.Instance.LoginRegistrationWindow_MouseDown);
                LoginRegistrationWindow.Instance.PanelContainer.Controls.Add(userControlRegistration);
                
            }
            
            LoginRegistrationWindow.Instance.PanelContainer.Controls["UserControlRegistration"].BringToFront();            
        }

        private void lblGuest_Click(object sender, EventArgs e)
        {
            LoginRegistrationWindow.Instance.Hide();
            MainWindow.Instance.Show();
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            this.txtUsername.Text = "";
            this.txtPassword.Text = "";
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
            }
            else
            {
                this.txtPassword.UseSystemPasswordChar = true;
            }
        }
    }
}
