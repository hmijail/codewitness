/*
 * Copyright ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package tech.pegasys.poc.witnesscodeanalysis.bytecodedump;

import org.apache.tuweni.bytes.Bytes;
import tech.pegasys.poc.witnesscodeanalysis.CodeAnalysisBase;


/**
 * Prints out the program counter offset, opcodes and parameter. For example:
 *
 * PC: 0x5d5d, opcode: PUSH1 0x20
 * PC: 0x5d5f, opcode: ADD
 * PC: 0x5d60, opcode: PUSH1 0x00
 * PC: 0x5d62, opcode: DUP2
 * PC: 0x5d63, opcode: MSTORE
 * PC: 0x5d64, opcode: PUSH1 0x20
 * PC: 0x5d66, opcode: ADD
 * PC: 0x5d67, opcode: PUSH1 0x00
 */
public class ByteCodeDump extends CodeAnalysisBase {
  public ByteCodeDump(Bytes code) {
    super(code);
  }

  public void dumpContract() {
    ByteCodePrinter printer = new ByteCodePrinter(this.code);
    printer.print(0, this.simple.getEndOfCode() + 1);
  }

  public static final String c2init = "608060405234801561001057600080fd5b50600360015560c1806100246000396000f3fe";
  public static final String c2 = "6080604052348015600f57600080fd5b506004361060325760003560e01c8063778b5892146037578063da1e128e146063575b600080fd5b606160048036036040811015604b57600080fd5b50803590602001356001600160f81b0319166083565b005b606160048036036040811015607757600080fd5b508035906020013560be565b8060018381548110609057fe5b90600052602060002090602091828204019190066101000a81548160ff021916908360f81c02179055505050565b806000838154811060cb57fe5b600091825260209091200155505056fea265627a7a723058209832b60d1aa283d2a1ca724b09775bd9232213e63c1e8eaf01c08741fd1b016864736f6c634300050a0032";
  public static final String c1 = "0x6080604052610160356000803561010052610100356101205281610140526020610140602461011c60a0355afa15156100385781610140525b506101405181036101a0358102829004811580156100555761017b565b60203561010052610120356101205261014035610140526020610120604461011c60a0355afa50610180356101205111600181146100c8576040356101005261010035610120526001610140526020610140602461011c60a0355afa5060016101405114156100c357600093505b6100cd565b600093505b50821580156100db57610179565b6060356101005261012035610120526000610140526020610140602461011c6101c0355afa151561010d576000610140525b610140518311600181146101725760803561010052610120356101205260c035610140526000610160526020610160604461011c6101c0355afa1515610154576000610160525b610160518411600181146101675761016c565b600095505b50610177565b600094505b505b505b504361020052610100356102205281610240526060610200f3fea165627a7a72305820111ab29018980c54baf1b8ca382223e13b3234a0761a8928284e27d9e80f67a00029";


  public static void main(String[] args) {
//    Bytes code = Bytes.fromHexString(ContractByteCode.contract_0x63de3096c22e89f175c8ed51ca0c129118516979);
//    Bytes code = Bytes.fromHexString(ContractByteCode.contract_0x6475593a8c52aac4059b1eb68235004f136eda5d);
    Bytes code = Bytes.fromHexString(c2);

    ByteCodeDump dump = new ByteCodeDump(code);
    //dump.showBasicInfo();
    dump.dumpContract();
  }
}