using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Teatrus.GUI
{
    public partial class LoginRegistrationWindow : Form
    {
        private static bool _dragging = false;
        private static Point _start_point = new Point(0, 0);
        static LoginRegistrationWindow instance;

        public LoginRegistrationWindow()
        {
            instance = this;
            InitializeComponent();
        }

        public static LoginRegistrationWindow Instance
        {
            get {                         
            if ((instance == null) || (instance.IsDisposed))
                {
                    instance = new LoginRegistrationWindow();
                }
                return instance;
            }
        }

        public Panel PanelContainer
        {
            get
            {
                return this.panelLoginRegistration;
            }
            set
            {
                this.panelLoginRegistration = value;
            }
        }

        public void LoginRegistrationWindow_MouseDown(object sender, MouseEventArgs e)
        {
            _dragging = true;
            _start_point = new Point(e.X, e.Y);
        }

        public void LoginRegistrationWindow_MouseMove(object sender, MouseEventArgs e)
        {
            if (_dragging)
            {
                Point p = PointToScreen(e.Location);
                Location = new Point(p.X - _start_point.X, p.Y - _start_point.Y);
            }
        }

        public void LoginRegistrationWindow_MouseUp(object sender, MouseEventArgs e)
        {
            _dragging = false;
        }
    }
}
