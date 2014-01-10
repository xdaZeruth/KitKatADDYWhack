package jobs;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import core.Job;

public class WalkToBank extends Job {

	public WalkToBank(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		if (ctx.players.local().getLocation().distanceTo(ctx.objects.select().id(6084).nearest().poll().getLocation())>=7&&(ctx.backpack.select().count()==28)){
	        return true;
			}
			else return false;
	}
	
	@Override
	public void execute() {

		System.out.println("WalkToBank");
        ctx.movement.stepTowards(new Tile(2837,10210,0));
        sleep(3000,5000);
        while (ctx.players.local().isInMotion()){
        	sleep(1000,2000);    
        }
	}
}