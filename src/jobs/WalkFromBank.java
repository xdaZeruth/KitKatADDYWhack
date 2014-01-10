package jobs;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;
import utils.Utils;
import core.Job;

public class WalkFromBank extends Job {
	
	private final int anyRock[] = {45067, 45068, 29233, 29235};
	   
	public WalkFromBank(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return ((ctx.backpack.select().isEmpty() && ctx.players.local().getLocation().distanceTo(ctx.objects.select().id(anyRock).nearest().poll().getLocation())<=6) || 
				((ctx.backpack.select().id(20406).count()==1) && (ctx.backpack.select().id(20407).count()==1)&&ctx.players.local().getLocation().distanceTo(ctx.objects.select().id(anyRock).nearest().poll().getLocation())>=6)) || 
				((ctx.backpack.select().id(20406).count()==2) || ctx.players.local().getLocation().distanceTo(ctx.objects.select().id(anyRock).nearest().poll().getLocation())>=6);
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