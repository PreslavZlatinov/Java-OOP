package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private HelperRepository<Helper> helperRepository;
    private PresentRepository<Present> presentRepository;

    private Shop shop;

    public ControllerImpl() {
        helperRepository = new HelperRepository<>();
        presentRepository = new PresentRepository<>();
        shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {

        Helper helper;

        if("Sleepy".equals(type)){
            helper = new Sleepy(helperName);
        } else if ("Happy".equals(type)){
            helper = new Happy(helperName);
        } else {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);

        return String.format(ADDED_HELPER,type,helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {

        if (helperRepository.findByName(helperName) == null){
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helperRepository.findByName(helperName).addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER,power,helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {

        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT,presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        List<Helper> helpers = helperRepository.getModels().stream().filter(helper -> helper.getEnergy() > 50).collect(Collectors.toList());

        if(helpers.isEmpty()){
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        int brokenInstruments = 0;
        
        Present present = presentRepository.findByName(presentName);

        for (Helper helper : helpers) {

            shop.craft(present,helper);

            brokenInstruments += (int) helper.getInstruments().stream().filter(Instrument::isBroken).count();

            if (present.isDone()){
                break;
            }
        }

        if (present.isDone()){
            return String.format(PRESENT_DONE,presentName,"done") + String.format(COUNT_BROKEN_INSTRUMENTS,brokenInstruments);
        }


        return String.format(PRESENT_DONE,presentName,"not done") + String.format(COUNT_BROKEN_INSTRUMENTS,brokenInstruments);
    }

    @Override
    public String report() {

        int countCraftedPresents = (int) presentRepository.getModels().stream().filter(Present::isDone).count();

        List<String> collect = helperRepository.getModels().stream().map(helper -> String.format("Name: %s%n" +
                "Energy: %d%n" +
                "Instruments: %d not broken left%n", helper.getName(), helper.getEnergy(),
                helper.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count())).collect(Collectors.toList());

        return String.format("%d presents are done!%n", countCraftedPresents) + String.format("Helpers info:%n") + String.join("", collect).trim();
    }
}
