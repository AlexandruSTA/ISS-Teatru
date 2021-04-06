
namespace Teatrus.GUI
{
    partial class MainWindow
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.userControlCentralDashboard1 = new Teatrus.UserControls.UserControlCentralDashboard();
            this.SuspendLayout();
            // 
            // userControlCentralDashboard1
            // 
            this.userControlCentralDashboard1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(46)))), ((int)(((byte)(51)))), ((int)(((byte)(73)))));
            this.userControlCentralDashboard1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.userControlCentralDashboard1.Location = new System.Drawing.Point(0, 0);
            this.userControlCentralDashboard1.Name = "userControlCentralDashboard1";
            this.userControlCentralDashboard1.Size = new System.Drawing.Size(1146, 577);
            this.userControlCentralDashboard1.TabIndex = 0;
            this.userControlCentralDashboard1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.MainWindow_MouseDown);
            this.userControlCentralDashboard1.MouseMove += new System.Windows.Forms.MouseEventHandler(this.MainWindow_MouseMove);
            this.userControlCentralDashboard1.MouseUp += new System.Windows.Forms.MouseEventHandler(this.MainWindow_MouseUp);
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1146, 577);
            this.Controls.Add(this.userControlCentralDashboard1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "MainWindow";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "MainWindow";
            this.ResumeLayout(false);

        }

        #endregion

        private UserControls.UserControlCentralDashboard userControlCentralDashboard1;
    }
}