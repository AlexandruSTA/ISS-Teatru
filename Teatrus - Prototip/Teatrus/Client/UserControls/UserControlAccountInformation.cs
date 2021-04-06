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
    public partial class UserControlAccountInformation : UserControl
    {
        public UserControlAccountInformation()
        {
            InitializeComponent();
            populateShowsAndPerformancesHistoryList();
        }

        public void populateShowsAndPerformancesHistoryList()
        {
            UserControlListItem[] listItems = new UserControlListItem[10];

            for (int i = 0; i < listItems.Length; i++)
            {
                listItems[i] = new UserControlListItem();
                listItems[i].Title = "Title " + (i + 1);
                listItems[i].Description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
                listItems[i].Button.Hide();
                flowLayoutPanelHistory.Controls.Add(listItems[i]);
               

            }

        }
    }


}
