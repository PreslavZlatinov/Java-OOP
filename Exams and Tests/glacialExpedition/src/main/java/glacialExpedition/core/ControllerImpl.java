package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploreStateCount;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {

        Explorer explorer;

        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        explorerRepository.add(explorer);

        return String.format(EXPLORER_ADDED,type,explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {

        State state = new StateImpl(stateName);

        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }

        stateRepository.add(state);

        return String.format(STATE_ADDED,stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {

        Explorer explorer = explorerRepository.byName(explorerName);

        if(explorer == null){
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST,explorerName));
        }

        explorerRepository.remove(explorer);

        return String.format(EXPLORER_RETIRED,explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        List<Explorer> collect = explorerRepository.getCollection().stream().filter(explorer -> explorer.getEnergy() > 50).collect(Collectors.toList());

        if(collect.isEmpty()){
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);

        Mission mission = new MissionImpl();

        mission.explore(state,collect);

        exploreStateCount++;

        long count = collect.stream().filter(explorer -> !explorer.canSearch()).count();

        return String.format(STATE_EXPLORER,stateName,count);
    }

    @Override
    public String finalResult() {
        return String.format(FINAL_STATE_EXPLORED + "%n",exploreStateCount) + String.format(FINAL_EXPLORER_INFO + "%n") +
                explorerRepository.getCollection().stream().map(Explorer::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
