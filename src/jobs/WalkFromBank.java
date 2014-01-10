package jobs;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;
import utils.Utils;
import core.Job;

public class WalkFromBank extends Job {
	   
	public WalkFromBank(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		double DistanceFromRock = (ctx.players.local().getLocation().distanceTo(new Tile(2875,10253,0)));
		return (ctx.backpack.select().isEmpty() && DistanceFromRock >=6);
	}
	
	@Override
	public void execute() {
		Utils.log("WalkFromBank");
		double DistanceFromRock = (ctx.players.local().getLocation().distanceTo(new Tile(2870,10251,0)));
		
		if(DistanceFromRock >=6)
        {
                ctx.movement.stepTowards(new Tile(2870,10251,0));
                sleep(3000,5000);
                while (ctx.players.local().isInMotion()){
                	sleep(1000,2000);
                }
        }
	}
}