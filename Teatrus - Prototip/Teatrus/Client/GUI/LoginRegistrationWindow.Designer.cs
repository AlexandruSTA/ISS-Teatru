
namespace Teatrus.GUI
{
    partial class LoginRegistrationWindow
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
            this.panelLoginRegistration = new System.Windows.Forms.Panel();
            this.userControlLogin = new Teatrus.UserControls.UserControlLogin();
            this.panelLoginRegistration.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelLoginRegistration
            // 
            this.panelLoginRegistration.Controls.Add(this.userControlLogin);
            this.panelLoginRegistration.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelLoginRegistration.Location = new System.Drawing.Point(0, 0);
            this.panelLoginRegistration.Name = "panelLoginRegistration";
            this.panelLoginRegistration.Size = new System.Drawing.Size(285, 544);
            this.panelLoginRegistration.TabIndex = 0;
            // 
            // userControlLogin
            // 
            this.userControlLogin.BackColor = System.Drawing.Color.White;
            this.userControlLogin.Dock = System.Windows.Forms.DockStyle.Fill;
            this.userControlLogin.Font = new System.Drawing.Font("Nirmala UI", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.userControlLogin.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(165)))), ((int)(((byte)(169)))));
            this.userControlLogin.Location = new System.Drawing.Point(0, 0);
            this.userControlLogin.Margin = new System.Windows.Forms.Padding(4);
            this.userControlLogin.Name = "userControlLogin";
            this.userControlLogin.Size = new System.Drawing.Size(285, 544);
            this.userControlLogin.TabIndex = 0;
            this.userControlLogin.MouseDown += new System.Windows.Forms.MouseEventHandler(this.LoginRegistrationWindow_MouseDown);
            this.userControlLogin.MouseMove += new System.Windows.Forms.MouseEventHandler(this.LoginRegistrationWindow_MouseMove);
            this.userControlLogin.MouseUp += new System.Windows.Forms.MouseEventHandler(this.LoginRegistrationWindow_MouseUp);
            // 
            // LoginRegistrationWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(285, 544);
            this.Controls.Add(this.panelLoginRegistration);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "LoginRegistrationWindow";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "LoginRegistrationWindow";
            this.MouseDown += new System.Windows.Forms.MouseEventHandler(this.LoginRegistrationWindow_MouseDown);
            this.MouseMove += new System.Windows.Forms.MouseEventHandler(this.LoginRegistrationWindow_MouseMove);
            this.MouseUp += new System.Windows.Forms.MouseEventHandler(this.LoginRegistrationWindow_MouseUp);
            this.panelLoginRegistration.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panelLoginRegistration;
        private UserControls.UserControlLogin userControlLogin;
    }
}