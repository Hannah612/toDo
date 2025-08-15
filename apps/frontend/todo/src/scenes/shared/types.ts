import styled from "@emotion/styled";

export enum SelectedPage {
  Home = "home",
  Tasks = "tasks",
  Footer = "footer",
}

export enum SortType {
  Date = "date",
  Priority = "priorityId",
  Completed = "completed",
  None = "none",
}

export enum OrderType {
  ASC = "asc",
  DESC = "desc",
  None = "none",
}

export interface Input {
    title: string;
    description: string;
    completed: boolean;
    priorityId: number;
    dueDate: Date;
    [key: string]: string | boolean | number | Date;
}

export const motionProps = {
  initial: "hidden",
  whileInView: "visible",
  viewport: {once:true, amount:0.5}, //amount = amount you need to see for animation to trigger 
  transition:{duration:0.2},
  variants: {
      hidden:{opacity:0, x:-50}, //initial state
      visible: {opacity:1, x:0}, //while in view state
  }
}

export interface Task {
  title: string;
  description?: string;
  priorityId: number;
  completed: boolean;
  dueDate: Date;
  categoryId: number;
  id: number;
  [key: string]: string | boolean | number | Date | undefined;
}

export const CalendarStyling = styled.div`
  .react-calendar__tile--now {
    background: #e6efe6;
    color: black;
  }

  .react-calendar__tile--active {
    background: #fa9de9;
    color: white;
  }

  .react-calendar__tile--hasActive {
    background: #fa9de9;
  }

  .react-calendar__tile--active:enabled:focus {
    background: #fa9de9;
  }

  .react-calendar {
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 5px;
    color: white;
  }
`;