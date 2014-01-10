package kitkataddywhack;
import java.util.ArrayList;
import java.util.List;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import core.Job;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.powerbot.event.PaintListener;
import utils.Utils;

@Manifest(name = "KitKatADDYWhack 0.1", description = "Mines addy, and gold when unavailable for money")
public class KitKatAddyWhack extends PollingScript implements PaintListener{
	private List<Job> jobList = new ArrayList<Job>();	
	public static long startTime = System.currentTimeMillis();

	
	@Override
    public void start() {
	jobList.add(new jobs.Mine(ctx));
	jobList.add(new jobs.WalkToBank(ctx));
	jobList.add(new jobs.WalkFromBank(ctx));
	jobList.add(new jobs.Banking(ctx));
	}
	
	@Override
    public void stop() {
		jobList.remove(2);
		jobList.remove(1);
		jobList.remove(0);
		}
	
	@Override
	public int poll() {
		for(Job job : jobList) {
		    if(job.validate()) {
		        job.execute();
		    }
		}
		return 0;
		}
	
	@Override
	public void repaint(Graphics g1) {
/*		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.BLACK);
		g.fillRect(222, 12, 298, 148);
		g.setColor(Color.CYAN);
		g.drawRect(220, 10, 300, 150);
		g.drawString("SuperGlass V0.70", 320, 30);
		g.drawString("So Far", 330, 50);
		g.drawString("Per Hour", 430, 50);
		g.drawString("Money:", 230, 70);
			g.drawString(""+Mine.money, 330, 70);
				g.drawString(""+(Utils.perHour(Mine.money)), 430, 70);
		g.drawString("Casts:", 230, 90);
			g.drawString(""+Mine.casts, 330, 90);
				g.drawString(""+(Utils.perHour(Mine.casts)), 430, 90);
		g.drawString("Mining XP:", 230, 110);
			g.drawString(""+Mine.miningxp, 330, 110);
				g.drawString(""+(Utils.perHour(Mine.miningxp)), 430, 110);
		g.drawString("Craft XP:", 230, 130);
			g.drawString(""+Heating.craftxp, 330, 130);
				g.drawString(""+(Utils.perHour(Heating.craftxp)), 430, 130);
*/	} 
	}
