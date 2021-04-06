using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Teatrus.UserControls
{
    public partial class UserControlListItem : UserControl
    {
        public UserControlListItem()
        {
            InitializeComponent();
        }

        #region Properties
        private string title;
        private string description;
        private Image icon;
        private Button buttonBuy;

        [Category("Custom Properties")]
        public string Title
        {
            get { return this.title; }
            set { this.title = value; lblTitle.Text = value; }
        }
        [Category("Custom Properties")]
        public string Description
        {
            get { return this.description; }
            set { this.description = value; lblDescription.Text = value; }
        }
        [Category("Custom Properties")]
        public Image Icon
        {
            get { return this.icon; }
            set { this.icon = value; pictureBoxIcon.Image = value; }
        }

        [Category("Custom Properties")]
        public Button Button
        {
            get { return btnBuyTicket; }
            set { this.buttonBuy = value;btnBuyTicket = value; }
        }

        #endregion
    }
}
