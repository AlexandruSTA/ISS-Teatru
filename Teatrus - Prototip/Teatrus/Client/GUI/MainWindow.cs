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

namespace Teatrus.GUI
{
    public partial class MainWindow : Form
    {

        [DllImport("Gdi32.dll", EntryPoint = "CreateRoundRectRgn")]

        private static extern IntPtr CreateRoundRectRgn(
            int nLeftRect,
            int nTopRect,
            int nRightRect,
            int nBottomRect,
            int nWidthEllipse,
            int nHeightEllipse);

        private static bool _dragging = false;
        private static Point _start_point = new Point(0, 0);
        static MainWindow instance;

        public MainWindow()
        {
            InitializeComponent();
            Region = System.Drawing.Region.FromHrgn(CreateRoundRectRgn(0, 0, Width, Height, 25, 25));
        }

        public static MainWindow Instance
        {
            get
            {
                if ((instance == null) || (instance.IsDisposed))
                {
                    instance = new MainWindow();
                }
                return instance;
            }
        }

        public void MainWindow_MouseDown(object sender, MouseEventArgs e)
        {
            _dragging = true;
            _start_point = new Point(e.X, e.Y);
        }

        public void MainWindow_MouseMove(object sender, MouseEventArgs e)
        {
            if (_dragging)
            {
                Point p = PointToScreen(e.Location);
                Location = new Point(p.X - _start_point.X, p.Y - _start_point.Y);
            }
        }

        public void MainWindow_MouseUp(object sender, MouseEventArgs e)
        {
            _dragging = false;
        }
    }
}
