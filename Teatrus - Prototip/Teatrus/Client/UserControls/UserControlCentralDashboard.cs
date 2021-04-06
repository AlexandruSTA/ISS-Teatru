using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using Teatrus.GUI;
using Teatrus.UserControls;

namespace Teatrus.UserControls
{
    public partial class UserControlCentralDashboard : UserControl
    {
        [DllImport("Gdi32.dll", EntryPoint = "CreateRoundRectRgn")]

        private static extern IntPtr CreateRoundRectRgn(
            int nLeftRect,
            int nTopRect,
            int nRightRect,
            int nBottomRect,
            int nWidthEllipse,
            int nHeightEllipse);

        public UserControlCentralDashboard()
        {
            InitializeComponent();
            Region = System.Drawing.Region.FromHrgn(CreateRoundRectRgn(0, 0, Width, Height, 25, 25));
            panelNavigation.Height = btnHome.Height;
            panelNavigation.Top = btnHome.Top;
            panelNavigation.Left = btnHome.Left;
            btnHome.BackColor = Color.FromArgb(46, 51, 73);
        }

        private void UserControlCentralDashboard_Load(object sender, EventArgs e)
        {

        }

        private void btnHome_Click(object sender, EventArgs e)
        {
            panelContainer.Controls.Clear();

            panelNavigation.Height = btnHome.Height;
            panelNavigation.Top = btnHome.Top;
            panelNavigation.Left = btnHome.Left;
            btnHome.BackColor = Color.FromArgb(46, 51, 73);
        }

        private void btnShowsAndPerformances_Click(object sender, EventArgs e)
        {
            panelContainer.Controls.Clear();

            panelNavigation.Height = btnShowsAndPerformances.Height;
            panelNavigation.Top = btnShowsAndPerformances.Top;
            panelNavigation.Left = btnShowsAndPerformances.Left;
            btnShowsAndPerformances.BackColor = Color.FromArgb(46, 51, 73);

            if (panelContainer.Controls["UserControlShowsAndPerformances"] == null)
            {
                UserControlShowsAndPerformances userControlShowsAndPerformances = new UserControlShowsAndPerformances();
                userControlShowsAndPerformances.Dock = DockStyle.Bottom;
                panelContainer.Controls.Add(userControlShowsAndPerformances);
            }

            panelContainer.Controls["UserControlShowsAndPerformances"].BringToFront();
        }

        private void btnCalendar_Click(object sender, EventArgs e)
        {
            panelContainer.Controls.Clear();

            panelNavigation.Height = btnCalendar.Height;
            panelNavigation.Top = btnCalendar.Top;
            panelNavigation.Left = btnCalendar.Left;
            btnCalendar.BackColor = Color.FromArgb(46, 51, 73);


            if (panelContainer.Controls["UserControlCalendar"] == null)
            {
                UserControlCalendar userControlCalendar = new UserControlCalendar();
                userControlCalendar.Dock = DockStyle.Bottom;
                panelContainer.Controls.Add(userControlCalendar);
            }

            panelContainer.Controls["UserControlCalendar"].BringToFront();
        }

        private void btnAccountInformation_Click(object sender, EventArgs e)
        {
            panelContainer.Controls.Clear();

            panelNavigation.Height = btnAccountInformation.Height;
            panelNavigation.Top = btnAccountInformation.Top;
            panelNavigation.Left = btnAccountInformation.Left;
            btnAccountInformation.BackColor = Color.FromArgb(46, 51, 73);


            if (panelContainer.Controls["UserControlAccountInformation"] == null)
            {
                UserControlAccountInformation userControlAccountInformation = new UserControlAccountInformation();
                userControlAccountInformation.Dock = DockStyle.Bottom;
                panelContainer.Controls.Add(userControlAccountInformation);
            }

            panelContainer.Controls["UserControlAccountInformation"].BringToFront();
        }

        private void btnSettings_Click(object sender, EventArgs e)
        {
            panelContainer.Controls.Clear();

            panelNavigation.Height = btnSettings.Height;
            panelNavigation.Top = btnSettings.Top;
            panelNavigation.Left = btnSettings.Left;
            btnSettings.BackColor = Color.FromArgb(46, 51, 73);


            if (panelContainer.Controls["UserControlSettings"] == null)
            {
                UserControlSettings userControlSettings = new UserControlSettings();
                userControlSettings.Dock = DockStyle.Bottom;
                panelContainer.Controls.Add(userControlSettings);
            }

            panelContainer.Controls["UserControlSettings"].BringToFront();

        }

        private void btnLogout_Click(object sender, EventArgs e)
        {
            panelContainer.Controls.Clear();

            panelNavigation.Height = btnLogout.Height;
            panelNavigation.Top = btnLogout.Top;
            panelNavigation.Left = btnLogout.Left;
            btnLogout.BackColor = Color.FromArgb(46, 51, 73);

            MainWindow.Instance.Hide();
            LoginRegistrationWindow.Instance.Show();
        }


        private void btnHome_Leave(object sender, EventArgs e)
        {
            btnHome.BackColor = Color.FromArgb(24,30,54);
        }

        private void btnShowsAndPerformances_Leave(object sender, EventArgs e)
        {
            btnShowsAndPerformances.BackColor = Color.FromArgb(24, 30, 54);
        }

        private void btnCalendar_Leave(object sender, EventArgs e)
        {
            btnCalendar.BackColor = Color.FromArgb(24, 30, 54);
        }

        private void btnAccountInformation_Leave(object sender, EventArgs e)
        {
            btnAccountInformation.BackColor = Color.FromArgb(24, 30, 54);
        }

        private void btnSettings_Leave(object sender, EventArgs e)
        {
            btnSettings.BackColor = Color.FromArgb(24, 30, 54);
        }

        private void btnLogout_Leave(object sender, EventArgs e)
        {
            
            btnLogout.BackColor = Color.FromArgb(24, 30, 54);
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }
}
