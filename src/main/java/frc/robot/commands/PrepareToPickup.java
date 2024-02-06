// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** Make sure the robot is in a state to pickup soda cans. */
public class PrepareToPickup extends SequentialCommandGroup {
  /**
   * Create a new prepare to pickup command.
   *
   * @param claw The claw subsystem to use
   * @param wrist The wrist subsystem to use
   * @param elevator The elevator subsystem to use
   */
  public PrepareToPickup(Elevator elevator) {
    addCommands(
        new SetElevatorSetpoint(0, elevator));
  }
}
