package jobs;
import java.util.concurrent.Callable;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.GroundItem;
import org.powerbot.script.wrappers.Item;

import utils.Utils;
import core.Job;

public class Mine extends Job {
	
	private final int goldRocks[] = {45067, 45068};
	private final int addyRock[] = {29233, 29235};
	private final int anyRock[] = {45067, 45068, 29233, 29235};
	   
	public Mine(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		return (ctx.backpack.select().count()<=27 && ctx.objects.select().id(anyRock).nearest().poll().isOnScreen());
	}
	
	@Override
	public void execute() {
		Utils.log("Mine");
		if (ctx.backpack.select().count() !=28){
			GroundItem ort = ctx.groundItems.select().id(24909).nearest().poll();
			Item ticket = ctx.backpack.select().id(24154).first().poll();
			while (ort.isValid()){
				ort.interact("Take");
				sleep(3000,5000);
			}
			while (ticket.isValid()){
				ticket.interact("Claim spin");
				sleep(3000,5000);
			}
			final GameObject rock = ctx.objects.select().id(addyRock).nearest().poll();
				if (ctx.backpack.select().count()!=28 && ctx.players.local().isIdle()){
					if (!rock.isOnScreen()){
						ctx.camera.turnTo(rock);
					}
					while ((ctx.players.local().getAnimation()!=12188 || ctx.players.local().getAnimation()==-1 
							 || ctx.players.local().getStance()==18020) && (rock.isValid()&&ctx.backpack.select().count()!=28)){
						rock.interact("Mine");
						sleep(3000,5000);	
					}
		            if(rock.isValid()){
						Utils.log("Addy Rock");
						while (rock.isValid()&&ctx.backpack.select().count()!=28){
		                if(Condition.wait(new Callable<Boolean>() {
		                    @Override
		                    public Boolean call() {
		                        return !rock.isValid();
		                    }
		                }, 350, 10));
						}
		            }
				}
				if (ctx.backpack.select().count()!=28){
					final GameObject arock = ctx.objects.select().id(addyRock).nearest().poll();
					final GameObject goldRock = ctx.objects.select().id(goldRocks).nearest().poll();
					if (ctx.backpack.select().count()!=28&&!arock.isValid() && ctx.players.local().isIdle()){
						if (!goldRock.isOnScreen()){
							ctx.camera.turnTo(goldRock);
						}
						while ((ctx.players.local().getAnimation()!=12188 || ctx.players.local().getAnimation()==-1 
								 || ctx.players.local().getStance()==18020) && (goldRock.isValid()&&ctx.backpack.select().count()!=28)){
							goldRock.interact("Mine");	
							sleep(3000,5000);	
						}
			            if(goldRock.isValid()){
							Utils.log("Gold Rock");
							while (goldRock.isValid()&&ctx.backpack.select().count()!=28){
			                if(Condition.wait(new Callable<Boolean>() {
			                    @Override
			                    public Boolean call() {
			                        return !goldRock.isValid();
			                    }
			                }, 350, 10));
							}
			            }				
					}
				}
			}
		}
}