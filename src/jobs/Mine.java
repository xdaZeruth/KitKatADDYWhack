package jobs;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Item;

import utils.Utils;
import core.Job;

public class Mine extends Job {
	
	private final int goldRocks[] = {45067, 45068};
	private final int addyRock[] = {29233, 29235};
	private final int anyRock[] = {45067, 45068, 29233, 29235};
	private int tries=0;
	   
	public Mine(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return (ctx.backpack.select().count()<=27 && ctx.players.local().getLocation().distanceTo(ctx.objects.select().id(anyRock).nearest().poll().getLocation())<=6);
	}
	
	@Override
	public void execute() {
		Utils.log("Mine");
		if (ctx.backpack.select().count() !=28){
			GroundItem ort = ctx.groundItems.select().id(24909).nearest().poll();
			Item ticket = ctx.backpack.select().id(24154).first().poll();
			Item fullUrn = ctx.backpack.select().id(20408).first().poll();
			while (ort.isValid()){
				ort.interact("Take");
				sleep(3000,5000);
			}
			while (ticket.isValid()){
				ticket.interact("Claim spin");
				sleep(3000,5000);
			}
			while (fullUrn.isValid()){
				fullUrn.interact("Teleport urn");
				sleep(3000,5000);
			}
			for (GameObject addyRockMine : ctx.objects.select().id(addyRock).nearest()) {
				if (!addyRockMine.isOnScreen()){
					ctx.camera.turnTo(addyRockMine);
				}
				if ((ctx.players.local().getAnimation()!=12188 && (ctx.players.local().getStance()!=18021)&&(ctx.players.local().getStance()!=18020)) || ctx.players.local().isIdle()){
					if (tries==3){
						ctx.camera.turnTo(addyRockMine);
						addyRockMine.interact("Mine");
						Utils.log("clicked");
						tries=0;
					}
					else{
					addyRockMine.interact("Mine");
					Utils.log("clicked");
					sleep(3000,5000);	
					tries+=1;
					}
				}
				else{
					sleep(3000,5000);
				}
				return;
			}

			for (GameObject goldRockMine : ctx.objects.select().id(goldRocks).nearest()) {
				final GameObject arock = ctx.objects.select().id(addyRock).nearest().poll();
				if (arock.isValid()){
					break;
				}
				if (!goldRockMine.isOnScreen()){
					ctx.camera.turnTo(goldRockMine);
				}
				if ((ctx.players.local().getAnimation()!=12188 && (ctx.players.local().getStance()!=18021)&&(ctx.players.local().getStance()!=18020)) || ctx.players.local().isIdle()){
					if (tries==3){
						ctx.camera.turnTo(goldRockMine);
						goldRockMine.interact("Mine");
						Utils.log("clicked");
						tries=0;
					}
					else{
					goldRockMine.interact("Mine");
					Utils.log("clicked");
					sleep(3000,5000);	
					tries+=1;
					}
				}
				else{
					sleep(3000,5000);
				}
				return;
			}
			
		}
		
	}
}