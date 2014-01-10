package jobs;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;

import utils.Utils;
import core.Job;

public class Banking extends Job {
	private final int bankID[] = {11402, 26972, 11758, 35647, 11402, 2213, 20980, 782, 6084};
	private final int decUrn = 20406;
	private final int partialDecUrn = 20407;

	
	public Banking(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
        return (ctx.players.local().getLocation().distanceTo(ctx.objects.select().id(6084).nearest().poll().getLocation())<=6&&(ctx.backpack.select().count()==28));
	}

	@Override
	public void execute() {
		System.out.println("Banking");
                	while (!ctx.bank.open()) {
                		ctx.objects.select().id(bankID).nearest();
                		sleep(300, 550);	
                    }
                	ctx.bank.depositInventory();
                	sleep(300,550);
                	if (ctx.bank.select().id(decUrn).count()>=1 && ctx.bank.select().id(partialDecUrn).count()<1){
                		ctx.bank.withdraw(decUrn, 1);
                	}
                	else if (ctx.bank.select().id(partialDecUrn).count()>=1){
                		ctx.bank.withdraw(partialDecUrn, 1);
                	}
                	if (Random.nextInt(1, 5)==5){ //randomize closing the bank.
                		ctx.bank.close();		  //not needed and can just map click.	  
                	}
                    Utils.log("---Banked---");
                }
           
	}

	


	
