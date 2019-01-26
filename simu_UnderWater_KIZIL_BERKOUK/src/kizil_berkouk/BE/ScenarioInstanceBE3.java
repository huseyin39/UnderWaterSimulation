package kizil_berkouk.BE;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import kizil_berkouk.BE.Scenarios.Scenario;
import kizil_berkouk.BE.Scenarios.ScenarioFeatures;

public class ScenarioInstanceBE3 implements IScenarioInstance{
	@Override
	public IScenario getScenarioInstance() {
		ScenarioFeatures kizil_berkouksf = new ScenarioFeatures("kizil_berkoukSF");
		
		
		LogicalDateTime start = new LogicalDateTime("20/01/2018 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		Scenario bms = new Scenario(new ScenarioId("Scenario3"), kizil_berkouksf, start, end);
		return bms;
		
	}

}
