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
    public partial class UserControlCalendar : UserControl
    {
        public UserControlCalendar()
        {
            InitializeComponent();
            markScheduledDays();
        }

        public void markScheduledDays()
        {
            DateTime[] dateTimes = new DateTime[20];
            for (int i = 0; i < 20; i++)
            {
                Random random = new Random(Guid.NewGuid().GetHashCode());

                DateTime dateTime = new DateTime(2021, i%12+1, random.Next(1,29));
                dateTimes[i] = dateTime;
            }
            monthCalendar.BoldedDates = dateTimes;
        }
    }
}
