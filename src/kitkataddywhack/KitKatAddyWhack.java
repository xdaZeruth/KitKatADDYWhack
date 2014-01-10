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
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.BLACK);
		g.fillRect(222, 12, 298, 148);
		g.setColor(Color.CYAN);
		g.drawRect(220, 10, 300, 150);
		g.drawString("Gold:", 320, 20);
		g.drawString("Casts:", 230, 50);
		g.drawString("Magic XP:", 320, 100);
		g.drawString("Craft XP:", 320, 150);


	//	g.drawRect(x, y, width, height);
		
		

	}
	}
