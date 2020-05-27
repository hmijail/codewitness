package tech.pegasys.poc.witnesscodeanalysis;

import org.apache.logging.log4j.Logger;
import org.apache.tuweni.bytes.Bytes;
import tech.pegasys.poc.witnesscodeanalysis.simple.ByteCodePrinter;

import static org.apache.logging.log4j.LogManager.getLogger;

public class WitnessCodeDump extends CodeAnalysisBase {
  private static final Logger LOG = getLogger();

  public WitnessCodeDump(Bytes code) {
    super(code);
  }

  public void dumpContract() {
    ByteCodePrinter printer = new ByteCodePrinter(this.code);
    printer.print(0, this.simple.getEndOfCode() + 1);
  }


  public static void main(String[] args) {
    Bytes code = Bytes.fromHexString(ContractByteCode.contract_0xd94ea6e43b7bffc9e4cba93f3ca49a191dc06d90);

    WitnessCodeDump dump = new WitnessCodeDump(code);
    dump.showBasicInfo();
    dump.dumpContract();
  }
}