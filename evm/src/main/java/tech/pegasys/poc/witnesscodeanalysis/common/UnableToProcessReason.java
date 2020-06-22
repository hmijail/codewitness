package tech.pegasys.poc.witnesscodeanalysis.common;

public enum UnableToProcessReason {
  CODECOPY_WITH_DYNAMIC_PARAMETERS,
  INVALID_JUMP_DEST,
  DYNAMIC_JUMP,
  END_OF_FUNCTION_ID_BLOCK_NOT_FOUND,
  CODE_PATHS_NOT_VALID,
  UNKNOWN_REASON1,
  UNKNOWN_REASON2,
  SUCCESS

}