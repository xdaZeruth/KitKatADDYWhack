package jobs;
import org.powerbot.script.methods.MethodContext;

import utils.Utils;
import core.Job;

public class Banking extends Job {
	private final int bankID[] = {11402, 26972, 11758, 35647, 11402, 2213, 20980, 782};

	
	public Banking(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public boolean validate() {
		if (ctx.objects.select().id(6084).nearest().poll().isOnScreen()){
        return (ctx.backpack.select().count()==28);
		}
		else return false;
	}

	@Override
	public void execute() {
		System.out.println("Banking");
                	while (!ctx.bank.open()) {
                		ctx.objects.select().id(bankID).nearest();
                		sleep(250, 350);	
                    }
                	ctx.bank.depositInventory();
                	sleep(300,500);
                    ctx.bank.close();
                    Utils.log("---Banked---");
                }
           
	}

	


	
