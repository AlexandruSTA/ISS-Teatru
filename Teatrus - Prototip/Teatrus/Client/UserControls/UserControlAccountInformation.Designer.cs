
namespace Teatrus.UserControls
{
    partial class UserControlAccountInformation
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lblCalendar = new System.Windows.Forms.Label();
            this.flowLayoutPanelHistory = new System.Windows.Forms.FlowLayoutPanel();
            this.SuspendLayout();
            // 
            // lblCalendar
            // 
            this.lblCalendar.AutoSize = true;
            this.lblCalendar.Font = new System.Drawing.Font("Monotype Corsiva", 21.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Italic))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCalendar.ForeColor = System.Drawing.SystemColors.ButtonFace;
            this.lblCalendar.Location = new System.Drawing.Point(152, 57);
            this.lblCalendar.Name = "lblCalendar";
            this.lblCalendar.Size = new System.Drawing.Size(520, 36);
            this.lblCalendar.TabIndex = 30;
            this.lblCalendar.Text = "History of Attended Shows and Performances";
            // 
            // flowLayoutPanelHistory
            // 
            this.flowLayoutPanelHistory.AutoScroll = true;
            this.flowLayoutPanelHistory.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.flowLayoutPanelHistory.Location = new System.Drawing.Point(0, 142);
            this.flowLayoutPanelHistory.Name = "flowLayoutPanelHistory";
            this.flowLayoutPanelHistory.Size = new System.Drawing.Size(862, 390);
            this.flowLayoutPanelHistory.TabIndex = 31;
            // 
            // UserControlAccountInformation
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.flowLayoutPanelHistory);
            this.Controls.Add(this.lblCalendar);
            this.Name = "UserControlAccountInformation";
            this.Size = new System.Drawing.Size(862, 532);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblCalendar;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanelHistory;
    }
}
